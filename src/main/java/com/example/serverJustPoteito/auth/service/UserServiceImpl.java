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
        User user;

        user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(email + " not found"));

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
    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("estibaliz.martines@elorrieta-errekamari.com");
        message.setTo(email);
        message.setSubject("Correo de recuperación de contraseña");
        message.setText("Otro correito de prueba ༼ つ ◕_◕ ༽つ");
        mailSender.send(message);

        return "Correo enviado";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found"));
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
}
