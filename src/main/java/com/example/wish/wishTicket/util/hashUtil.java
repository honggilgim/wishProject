package com.example.wish.wishTicket.util;

import com.zaxxer.hikari.pool.HikariProxyCallableStatement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



public class hashUtil {

    // 해시 문자
    private static final char[] CHARSET = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    public static String generate12CharHashWithBase64(String input) {
        try {
            // SHA-256 해시 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // 바이트 배열을 소문자 + 숫자 해시로 변환
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                // 바이트 값을 CHARSET의 인덱스로 변환
                int index = (b & 0xFF) % CHARSET.length; // & 0xFF로 음수 방지
                hashBuilder.append(CHARSET[index]);
                if (hashBuilder.length() >= 12) {
                    break; // 12자만 생성
                }
            }

            return hashBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash algorithm not found", e);
        }
    }
}