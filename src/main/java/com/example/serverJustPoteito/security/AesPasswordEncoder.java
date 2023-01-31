package com.example.serverJustPoteito.security;

import com.example.serverJustPoteito.auth.model.EncryptPostRequest;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.spec.KeySpec;
import java.util.Arrays;

@Component
public class AesPasswordEncoder {

    private static byte[] salt = "esta es la salt!".getBytes(); // string de 16 bytes
    private static final String EMAIL_FILE_PATH = "Email.dat";
    private static final String PASSWORD_FILE_PATH = "Password.dat";

    public String cifrarEmail(EncryptPostRequest encryptPostRequest) {
        String ret = null;
        KeySpec keySpec;
        SecretKeyFactory secretKeyFactory;
        FileOutputStream fileOutputStream;
        try {

            keySpec = new PBEKeySpec(encryptPostRequest.getClave().toCharArray(), salt, 65536, 128); // AES-128
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] encodedMessage = cipher.doFinal(encryptPostRequest.getMensaje().getBytes()); // Mensaje cifrado !!!
            byte[] iv = cipher.getIV(); // vector de inicializaci�n por modo CBC

            byte[] combined = concatArrays(iv, encodedMessage);

            fileOutputStream = new FileOutputStream(EMAIL_FILE_PATH);
            fileOutputStream.write(combined);

            ret = new String(encodedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public String cifrarPassword(EncryptPostRequest encryptPostRequest) {
        String ret = null;
        KeySpec keySpec;
        SecretKeyFactory secretKeyFactory;
        FileOutputStream fileOutputStream;
        try {

            keySpec = new PBEKeySpec(encryptPostRequest.getClave().toCharArray(), salt, 65536, 128); // AES-128
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] encodedMessage = cipher.doFinal(encryptPostRequest.getMensaje().getBytes()); // Mensaje cifrado !!!
            byte[] iv = cipher.getIV(); // vector de inicializaci�n por modo CBC

            byte[] combined = concatArrays(iv, encodedMessage);

            fileOutputStream = new FileOutputStream(PASSWORD_FILE_PATH);
            fileOutputStream.write(combined);

            ret = new String(encodedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    String descifrarEmail(String clave) {
        String ret = null;
        try {
            File fichero = new File(EMAIL_FILE_PATH);
            byte[] fileContent = Files.readAllBytes(fichero.toPath());
            KeySpec keySpec = null;
            SecretKeyFactory secretKeyFactory = null;

            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128); // AES-128
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16)); // La IV esta aqui
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
            ret = new String(decodedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    String descifrarPassword(String clave) {
        String ret = null;
        try {
            File fichero = new File(PASSWORD_FILE_PATH);
            byte[] fileContent = Files.readAllBytes(fichero.toPath());
            KeySpec keySpec = null;
            SecretKeyFactory secretKeyFactory = null;

            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128); // AES-128
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16)); // La IV esta aqui
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
            ret = new String(decodedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private byte[] concatArrays(byte[] array1, byte[] array2) {
        byte[] ret = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, ret, 0, array1.length);
        System.arraycopy(array2, 0, ret, array1.length, array2.length);
        return ret;
    }
}
