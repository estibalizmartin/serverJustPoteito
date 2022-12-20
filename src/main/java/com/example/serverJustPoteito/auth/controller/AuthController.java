package com.example.serverJustPoteito.auth.controller;

import com.example.serverJustPoteito.auth.Exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.model.AuthRequest;
import com.example.serverJustPoteito.auth.model.AuthResponse;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.service.UserService;
import com.example.serverJustPoteito.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> signIn(@RequestBody AuthRequest request) throws UserCantCreateException {
        // TODO solo esta creado en el caso de que funcione. Si no es posible que de 500
        //User user = new User(request.getEmail(), request.getPassword());
        //return new ResponseEntity<Integer>(userService.create(user), HttpStatus.CREATED);
        User user = new User(request.getName(), request.getSurnames(), request.getUserName(), request.getEmail(), request.getPassword());
        userService.signUp(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        int idUser = userDetails.getId();  //Sacamos el ID del usuario logueado de aquí en vez de hacerlo de la URL
        return ResponseEntity.ok().body(userDetails);
    }


}
