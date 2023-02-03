package com.example.serverJustPoteito.auth.service;

import com.example.serverJustPoteito.auth.model.*;
import com.example.serverJustPoteito.auth.exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.persistence.Role;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.repository.RoleRepository;
import com.example.serverJustPoteito.auth.repository.UserRepository;
import com.example.serverJustPoteito.security.CustomPasswordEncoder;
import com.example.serverJustPoteito.security.RsaKeyHandler;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User signUp(User user) throws UserCantCreateException {
        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        Role userRole = roleRepository.findByName(RoleTypeEnum.USER.name()).get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        user.setEnabled(true);
        user.setRoles(roles);

        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new UserCantCreateException(e.getMessage());
        }
    }

    @Override
    public User signUpCrypted(User user) throws UserCantCreateException {
        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();

        String decryptedPass = RsaKeyHandler.decryptText(user.getPassword());
        String password = passwordEncoder.encode(decryptedPass);
        user.setPassword(password);

        Role userRole = roleRepository.findByName(RoleTypeEnum.USER.name()).get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        user.setEnabled(true);
        user.setRoles(roles);

        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new UserCantCreateException(e.getMessage());
        }
    }

    @Override
    public UserServiceModel logUser(String email, String password) {
        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
        String decryptedPass = RsaKeyHandler.decryptText(password);
        User user = loadUserByEmail(email);

        UserServiceModel userResponse = new UserServiceModel();


        if (user == null) {
            userResponse.setId(-1);
        } else if (passwordEncoder.matches(decryptedPass, user.getPassword())) {

            if (!user.isEnabled()) {
                userResponse.setId(-3);
                return userResponse;
            }

            userResponse = new UserServiceModel(
                    user.getId(),
                    user.getName(),
                    user.getSurnames(),
                    user.getUserName(),
                    user.getEmail(),
                    null,
                    user.isEnabled(),
                    user.getRoles()
            );

        } else {
            userResponse.setId(-2);
        }
        return userResponse;
    }

    @Override
    public boolean sendEmail(String email) {
        String newPassword = generatePassayPassword();

        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        User user = loadUserByEmail(email);

        int queryResult = 0;
        if (user != null) {
            queryResult = userRepository.resetPassword(encodedNewPassword, email);
        }

        if (queryResult == 1) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Correo de recuperación de contraseña");
            message.setText("Txaber cómeme los cojones con esta contraseña: " + newPassword);
            mailSender.send(message);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserServiceModel> getUsers() {
        List<User> users = (List<User>) userRepository.findAll();

        System.out.println(users.get(2).toString());

        List<UserServiceModel> response = new ArrayList<>();

        for (User user : users) {
            response.add(new UserServiceModel(
                    user.getId(),
                    user.getName(),
                    user.getSurnames(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.isEnabled(),
                    user.getRoles()
            ));

        }

        return response;
    }

    @Override
    public UserServiceModel getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Usuario no encontrado.")
        );

        UserServiceModel response = new UserServiceModel(
                user.getId(),
                user.getName(),
                user.getSurnames(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.getRoles()
        );

        return response;
    }

    @Override
    public UserServiceModel createUser(UserPostRequest userPostRequest) {

        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userPostRequest.getPassword());
        User user = new User(
                null,
                userPostRequest.getName(),
                userPostRequest.getSurnames(),
                userPostRequest.getUserName(),
                userPostRequest.getEmail(),
                encodedPassword,
                userPostRequest.isEnabled(),
                null
        );

        Set<Role> roles = new HashSet<Role>();
        for (Role role  : userPostRequest.getRoles())
            roles.add(role);

        user.setRoles(roles);

        user = userRepository.save(user);

        UserServiceModel response = new UserServiceModel(
                user.getId(),
                user.getName(),
                user.getSurnames(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.getRoles()
        );

        return response;
    }

    @Override
    public UserServiceModel updateUser(Integer id, UserPostRequest userPostRequest) {

        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userPostRequest.getPassword());

        User user = new User(
                id,
                userPostRequest.getName(),
                userPostRequest.getSurnames(),
                userPostRequest.getUserName(),
                userPostRequest.getEmail(),
                encodedPassword,
                userPostRequest.isEnabled(),
                null
        );

        Set<Role> roles = new HashSet<>();

        for (Role role: userPostRequest.getRoles()) {
            if (role.getName().equals((RoleTypeEnum.USER.name()))) {
                roles.add(roleRepository.findByName(RoleTypeEnum.USER.name()).get());
            }
            if (role.getName().equals((RoleTypeEnum.ADMIN.name()))) {
                roles.add(roleRepository.findByName(RoleTypeEnum.ADMIN.name()).get());
            }
        }

        user.setRoles(roles);

        user = userRepository.save(user);


        UserServiceModel response = new UserServiceModel(
                user.getId(),
                user.getName(),
                user.getSurnames(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.getRoles()
        );

        return response;
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public int changeUserPasswordNoToken(PasswordPostRequest passwordPostRequest) {

        String newPassword = RsaKeyHandler.decryptText(passwordPostRequest.getNewPassword());
        String oldPassword = RsaKeyHandler.decryptText(passwordPostRequest.getOldPassword());

        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        Optional<User> optionalUser = userRepository.findById(passwordPostRequest.getId());
        User user = null;

        if (!optionalUser.isEmpty()) {
            user = optionalUser.get();
        } else {
            return -2;
        }

        if (user == null) return -2;

        int queryResult = 0;
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            queryResult = userRepository.updatePassword(
                    encodedNewPassword,
                    passwordPostRequest.getId()
            );
            return queryResult;
        }
        else
            return -1;
    }

    private User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Email " + email + " not found"));
    }

    private String generatePassayPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }

    @Override
    public boolean isAlreadyExists(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found."));
    }
}
