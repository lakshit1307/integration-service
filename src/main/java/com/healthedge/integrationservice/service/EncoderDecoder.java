package com.healthedge.integrationservice.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Service
public class EncoderDecoder {

    public String encrypt(String strClearText,String strKey) throws Exception{
        String strData="";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted=cipher.doFinal(strClearText.getBytes());
            strData=new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }

    public String decrypt(String strEncrypted,String strKey) throws Exception{
        String strData="";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
            strData=new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }

//    public String encrypt(String memberId){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(memberId);
//        String salt = KeyGenerators.string().generateKey();
//        String passWord="Tenant1 password";
//        TextEncryptor encryptor = Encryptors.text(passWord, salt);
//
//
//        String textToEncrypt = "*royal secrets*";
//        String encryptedText = encryptor.encrypt(textToEncrypt);
//
//        // Could reuse encryptor but wanted to        show reconstructing TextEncryptor
//        TextEncryptor decryptor = Encryptors.text(passWord, salt);
//        String decryptedText = decryptor.decrypt(encryptedText);
//
//        if(textToEncrypt.equals(decryptedText)) {
//            System.out.println("Success: decrypted text matches");
//        } else {
//            System.out.println("Failed: decrypted text does not match");
//        }
//        return decryptedText;
//    }
}
