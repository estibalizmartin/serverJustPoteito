package com.example.serverJustPoteito.security;

import javax.crypto.Cipher;
import java.io.*;
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


    public static void generateKeys() {

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024); // Inicializamos el tamanio a 1024 bits
            KeyPair keypair = generator.generateKeyPair();
            PublicKey publicKey = keypair.getPublic(); // Clave Publica
            PrivateKey privateKey = keypair.getPrivate(); // Clave Privada

            // Publica
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            FileOutputStream fileOutputStream = new FileOutputStream(PUBLIC_KEY_FILE_PATH);
            fileOutputStream.write(x509EncodedKeySpec.getEncoded());
            fileOutputStream.close();

            // Privada
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            fileOutputStream = new FileOutputStream(PRIVATE_KEY_FILE_PATH);
            fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] cifrarTexto(String mensaje) {
        byte[] encodedMessage = null;
        try {
            // Clave publica
            File ficheroPublica = new File(PUBLIC_KEY_FILE_PATH);
            byte[] clavePublica = Files.readAllBytes(ficheroPublica.toPath());
            System.out.println("Tamanio -> " + clavePublica.length + " bytes");

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(clavePublica);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encodedMessage = cipher.doFinal(mensaje.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedMessage;
    }

    private static byte[] descifrarTexto(byte[] mensaje) {
        byte[] decodedMessage = null;
        try {
            // Clave publica
            File ficheroPrivada = new File(PRIVATE_KEY_FILE_PATH);
            byte[] clavePrivada = Files.readAllBytes(ficheroPrivada.toPath());
            System.out.println("Tamanio -> " + clavePrivada.length + " bytes");

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(clavePrivada);
            PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decodedMessage = cipher.doFinal(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedMessage;
    }

    public static void main(String[] args) {
        //generateKeys();

        byte[] mensajeCifrado = cifrarTexto("Mensaje super secreto");
        System.out.println("Cifrado! -> " + new String(mensajeCifrado));
        System.out.println("Tamanio -> " + mensajeCifrado.length + " bytes");
        System.out.println("-----------");
        byte[] mensajeDescifrado = descifrarTexto(mensajeCifrado);
        System.out.println("Descifrado! -> " + new String(mensajeDescifrado));
        System.out.println("-----------");
        System.out.println("Ficheros de Clave Generados!");
    }

}

