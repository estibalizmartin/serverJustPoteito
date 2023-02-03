package com.example.serverJustPoteito.auth;

import com.example.serverJustPoteito.auth.exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.model.*;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.service.UserService;
import com.example.serverJustPoteito.security.AesPasswordEncoder;
import com.example.serverJustPoteito.security.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private AesPasswordEncoder aesPasswordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getId(), user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@RequestBody AuthRequest request) {
        // TODO solo esta creado en el caso de que funcione. Si no es posible que de 500
        //User user = new User(request.getEmail(), request.getPassword());
        //return new ResponseEntity<Integer>(userService.create(user), HttpStatus.CREATED);
        User user = new User(request.getName(), request.getSurnames(), request.getUserName(), request.getEmail(), request.getPassword());
        try {
            userService.signUp(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserCantCreateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        int idUser = userDetails.getId();  //Sacamos el ID del usuario logueado de aquí en vez de hacerlo de la URL
        return ResponseEntity.ok().body(userDetails);
    }

    @GetMapping("/auth/users/{id}")
    public ResponseEntity<UserServiceModel> getUserById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<UserServiceModel> createUser(@Valid @RequestBody UserPostRequest userPostRequest) {
        UserServiceModel userResponse = userService.createUser(userPostRequest);

        if (userResponse.getId() == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        }
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<UserServiceModel> updateUser(
            @PathVariable("id") Integer id,
            @Valid @RequestBody UserPostRequest userPostRequest) {

        UserServiceModel userResponse = userService.updateUser(id, userPostRequest);

        if (userResponse.getId() == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            if (userService.isAlreadyExists(id)) {
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
            }
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Integer> deleteUserById(@PathVariable("id") Integer id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario no encontrado.");
        }
    }

    //DI Y ANDROID

    @PostMapping("/notoken/createUser")
    public ResponseEntity<UserServiceModel> createUserNoToken( @RequestBody UserPostRequest userPostRequest) {
        UserServiceModel userResponse = userService.createUser(userPostRequest);

           return new ResponseEntity<>(userService.createUser(userPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/notoken/update/{id}")
    public ResponseEntity<UserServiceModel> updateUserNoToken(
            @PathVariable("id") Integer id,
            @Valid @RequestBody UserPostRequest userPostRequest) {
        if (userService.isAlreadyExists(id)) {
            return new ResponseEntity<>(userService.updateUser(id, userPostRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.updateUser(id, userPostRequest), HttpStatus.CREATED);
        }
    }

    @PostMapping("/notoken/delete/{id}")
    public ResponseEntity<Integer> deleteUserByIdNoToken(@PathVariable("id") Integer id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario no encontrado.");
        }
    }

    @PostMapping("/auth/signupCrypted")
    public ResponseEntity<?> signUpCrypted(@RequestBody AuthRequest request) {
        // TODO solo esta creado en el caso de que funcione. Si no es posible que de 500
        //User user = new User(request.getEmail(), request.getPassword());
        //return new ResponseEntity<Integer>(userService.create(user), HttpStatus.CREATED);
        User user = new User(request.getName(), request.getSurnames(), request.getUserName(), request.getEmail(), request.getPassword());
        try {
            userService.signUpCrypted(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserCantCreateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/notoken/login")
    public ResponseEntity<UserServiceModel> loginNoToken(@Valid @RequestBody AuthRequest request){
        UserServiceModel userResponse = userService.logUser(request.getEmail(), request.getPassword());
        if (userResponse.getId() == -1) {
            return ResponseEntity.status(433).build();
        } else if (userResponse.getId() == -2) {
            return ResponseEntity.status(434).build();
        } else if (userResponse.getId() == -3) {
            return ResponseEntity.status(435).build();
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
        }

    }

    @GetMapping("/auth/users")
    public ResponseEntity<List<UserServiceModel>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<Boolean> sendEmail(@RequestBody String email) {
        return new ResponseEntity<>(userService.sendEmail(email), HttpStatus.OK);
    }

    @PostMapping("/encryptemail")
    public ResponseEntity<String> encryptEmailAddress(@RequestBody EncryptPostRequest encryptPostRequest) {
        return new ResponseEntity<>(aesPasswordEncoder.cifrarEmail(encryptPostRequest), HttpStatus.OK);
    }

    @PostMapping("/encryptpassword")
    public ResponseEntity<String> encryptEmailPassword(@RequestBody EncryptPostRequest encryptPostRequest) {
        return new ResponseEntity<>(aesPasswordEncoder.cifrarPassword(encryptPostRequest), HttpStatus.OK);
    }

    @PutMapping("/changepasswordnotoken")
    public ResponseEntity<Integer> changeUserPasswordNoToken(
            @RequestBody PasswordPostRequest passwordPostRequest
    ) {
        int passwordChanged = userService.changeUserPasswordNoToken(passwordPostRequest);

        if (passwordChanged == -1) {
            return ResponseEntity.status(434).build();
        } else if (passwordChanged == -2) {
            return ResponseEntity.status(433).build();
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(passwordChanged);
        }
    }
}
