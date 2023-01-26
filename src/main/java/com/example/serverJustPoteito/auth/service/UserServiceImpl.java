package com.example.serverJustPoteito.auth.service;

import com.example.serverJustPoteito.auth.Exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.model.RoleTypeEnum;
import com.example.serverJustPoteito.auth.model.UserPostRequest;
import com.example.serverJustPoteito.auth.persistence.Role;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.model.UserServiceModel;
import com.example.serverJustPoteito.auth.repository.RoleRepository;
import com.example.serverJustPoteito.auth.repository.UserRepository;
import com.example.serverJustPoteito.security.CustomPasswordEncoder;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private JavaMailSender mailSender;

        public UserServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

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
    public List<String> logUser(String email, String password) {
        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
        List<String> response = new ArrayList<>();

        User user = loadUserByEmail(email);

        if (user == null) {
            response.add("-1");
            return response;
        } else if (passwordEncoder.matches(password, user.getPassword())) {
            response.add("" + user.getId());
            response.add(user.getUsername());
            return response;
        } else {
            response.add("-2");
            return response;
        }
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
            message.setFrom("estibaliz.martines@elorrieta-errekamari.com");
            message.setTo(email);
            message.setSubject("Correo de recuperación de contraseña");
            message.setText("Tu nueva contraseña es: " + newPassword);
            mailSender.send(message);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserServiceModel> getUsers(int limit, int offset) {
        List<User> users = userRepository.findAllFiltered(limit, offset);

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
                true,
                null
        );

        return response;
    }

    @Override
    public UserServiceModel createUser(UserPostRequest userPostRequest) {
        User user = new User(
                null,
                userPostRequest.getName(),
                userPostRequest.getSurnames(),
                userPostRequest.getUserName(),
                userPostRequest.getEmail(),
                userPostRequest.getPassword(),
                true,
                null
        );

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
        User user = new User(
                id,
                userPostRequest.getName(),
                userPostRequest.getSurnames(),
                userPostRequest.getUserName(),
                userPostRequest.getEmail(),
                userPostRequest.getPassword(),
                true,
                null
        );

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
    public boolean isAlreadyExists(Integer id) {
        return userRepository.existsById(id);
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
}
