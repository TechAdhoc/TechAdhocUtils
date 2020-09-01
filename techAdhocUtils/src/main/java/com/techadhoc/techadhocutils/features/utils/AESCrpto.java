package com.techadhoc.techadhocutils.features.utils;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
//SHA256 generator 16
public class AESCrpto
{
    private static final String ALGORITHM = "AES";
    private static final String KEY = "6d3c2e07c56c4163";
    public static String encrypt(String value) throws Exception
    {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESCrpto.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
        return encryptedValue64;

    }

    public static String decrypt(String value) throws Exception
    {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESCrpto.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");
        return decryptedValue;

    }

    private static Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(AESCrpto.KEY.getBytes(), AESCrpto.ALGORITHM);
        return key;
    }
}