package com.example.serverJustPoteito.auth;

import com.example.serverJustPoteito.auth.Exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.model.AuthRequest;
import com.example.serverJustPoteito.auth.model.AuthResponse;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.model.UserServiceModel;
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


    @PostMapping("/auth/signup")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest request) {
        // TODO solo esta creado en el caso de que funcione. Si no es posible que de 500
        //User user = new User(request.getEmail(), request.getPassword());
        //return new ResponseEntity<Integer>(userService.create(user), HttpStatus.CREATED);
        User user = new User(request.getName(), request.getSurnames(), request.getUserName(), request.getEmail(), request.getPassword());
        try {
            userService.signUp(user);
        } catch (UserCantCreateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        int idUser = userDetails.getId();  //Sacamos el ID del usuario logueado de aquí en vez de hacerlo de la URL
        return ResponseEntity.ok().body(userDetails);
    }

    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<List<UserServiceModel>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<UserServiceModel> createUser(@Valid @RequestBody AuthRequest authRequest) {
        return new ResponseEntity<>(userService.createUser(authRequest), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/edit")
    public ResponseEntity<UserServiceModel> updateUser(
            @PathVariable("id") Integer id,
            @Valid @RequestBody AuthRequest authRequest) {
        if (userService.isAlreadyExists(id)) {
            return new ResponseEntity<>(userService.updateUser(id, authRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.updateUser(id, authRequest), HttpStatus.CREATED);
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteUserById(@PathVariable("id") Integer id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario no encontrado.");
        }
    }
}
