package com.example.blogmanagersystem.Utils;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) {
        // 生成一个长度为32字节的随机密钥
        byte[] bytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);

        // 将随机生成的字节数组转换为Base64编码的字符串
        String base64EncodedKey = Base64.getEncoder().encodeToString(bytes);

        System.out.println("Generated Key: " + base64EncodedKey);
    }
}

