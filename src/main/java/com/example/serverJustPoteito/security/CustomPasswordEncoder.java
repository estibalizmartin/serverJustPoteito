package com.example.serverJustPoteito.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return cifrarTexto(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return cifrarTexto(rawPassword.toString()).equals(encodedPassword);
    }

    public String cifrarTexto(String texto) {
        String textoCifrado = "";

        MessageDigest algoritmo;

        try {

            algoritmo = MessageDigest.getInstance("SHA");
            algoritmo.reset();
            byte[] dataBytes = texto.getBytes();
            algoritmo.update(dataBytes);
            byte[] resumen = algoritmo.digest();
            textoCifrado = convertBytesToHex(resumen);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return textoCifrado;
    }

    private static String convertBytesToHex(byte[] resumen) {
        String hex = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1)
                hex += "0";
            hex += h;
        }
        return hex.toString();
    }
}
