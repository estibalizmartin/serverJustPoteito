package com.example.serverJustPoteito.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return cifrarTexto(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return cifrarTexto(rawPassword.toString()).equals(encodedPassword);
    }

    private String cifrarTexto(String texto) {
        String textoCifrado = "";

        // TODO realizar el método criptográfico

        return textoCifrado;
    }
}
