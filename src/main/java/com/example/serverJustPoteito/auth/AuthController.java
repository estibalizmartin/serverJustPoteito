package com.example.serverJustPoteito.auth;

import com.example.serverJustPoteito.auth.Exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.model.*;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.service.UserService;
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
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    UserService userService;

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

    @PostMapping("/loginnotoken")
    public ResponseEntity<List<String>> loginNoToken(@RequestBody AuthRequest request){
        List<String> response = userService.logUser(request.getEmail(), request.getPassword());
        if (response.get(0).equals("-1")) {
            return ResponseEntity.status(432).build();
        } else if (response.get(0).equals("-2")) {
            return ResponseEntity.status(433).build();
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
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

    @PostMapping("/forgotpassword")
    public ResponseEntity<Boolean> sendEmail(@RequestBody String email) {
        return new ResponseEntity<>(userService.sendEmail(email), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserServiceModel>> getUsers(@RequestParam int limit,
                                                           @RequestParam int offset) {
        return new ResponseEntity<>(userService.getUsers(limit, offset), HttpStatus.OK);
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<UserServiceModel> getUserById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<UserServiceModel> createUser(@Valid @RequestBody UserPostRequest userPostRequest) {
        return new ResponseEntity<>(userService.createUser(userPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity<UserServiceModel> updateUser(
            @PathVariable("id") Integer id,
            @Valid @RequestBody UserPostRequest userPostRequest) {
        if (userService.isAlreadyExists(id)) {
            return new ResponseEntity<>(userService.updateUser(id, userPostRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.updateUser(id, userPostRequest), HttpStatus.CREATED);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteUserById(@RequestBody Integer id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario no encontrado.");
        }
    }

    @PostMapping("/changepasswordnotoken")
    public ResponseEntity<Integer> changeUserPasswordNoToken(
            @RequestBody PasswordPostRequest passwordPostRequest
    ) {
        int passwordChanged = userService.changeUserPasswordNoToken(passwordPostRequest);

        if (passwordChanged == -1) {
            return ResponseEntity.status(432).build();
        } else if (passwordChanged == -2) {
            return ResponseEntity.status(433).build();
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(passwordChanged);
        }
    }
}
