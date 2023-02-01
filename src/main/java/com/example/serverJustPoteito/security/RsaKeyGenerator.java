package com.example.serverJustPoteito.security;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaKeyGenerator {

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

    public static void main(String[] args) {
        generateKeys();
    }
}
