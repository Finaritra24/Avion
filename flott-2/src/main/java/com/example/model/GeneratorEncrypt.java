package com.example.model;

import java.util.Base64;    
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;   
import javax.crypto.SecretKey;
public class GeneratorEncrypt{
    static Cipher cipher;
    KeyGenerator keyGenerator;
    SecretKey secretKey;
    public GeneratorEncrypt()throws Exception{
        keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance("AES");
    }
    public String generateEncrypt(Log log)throws Exception{
        byte[] plainTextByte = log.getMdp().getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }
    public String generateEncrypt2(String log)throws Exception{
        byte[] plainTextByte = log.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }
    public String refreshtoken(Token tok)throws Exception{
        String encrypt=tok.getLog().getMdp();
        return generateEncrypt2(encrypt);
    }
}
