package com.aha.common.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptorUtils {

//    private static final String PASSWORD_INFO = "ZdrFZN7zVNVwRC#6";

    private static final String PASSWORD_INFO = "yygl";
    public static void main(String[] args) {

        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setPassword(PASSWORD_INFO);
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
//        standardPBEStringEncryptor.setSaltGenerator(new ZeroSaltGenerator());
        // 解密后的文本
        String redisPwd = standardPBEStringEncryptor.encrypt("redis");
        System.out.println("redis 加密之后结果: " + redisPwd);
        System.out.println("redis 解密之后结果: " + standardPBEStringEncryptor.decrypt(redisPwd));

    }

}
