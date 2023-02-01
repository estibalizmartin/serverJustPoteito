package com.example.serverJustPoteito.security;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaKeyHandler {

    private static final String PUBLIC_KEY_FILE_PATH = "./src/main/resources/keys/public.key";
    private static final String PRIVATE_KEY_FILE_PATH = "./src/main/resources/keys/private.key";


    public static String decryptText(String mensaje) {
        String decodedMessage = null;
        try {

            File ficheroPrivada = new File(PRIVATE_KEY_FILE_PATH);
            byte[] clavePrivada = Files.readAllBytes(ficheroPrivada.toPath());

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(clavePrivada);
            PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decodedMessage = new String(cipher.doFinal(Base64.getDecoder().decode(mensaje)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedMessage;
    }


}

