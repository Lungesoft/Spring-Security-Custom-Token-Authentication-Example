package com.lungesoft.token.example.util;

import com.lungesoft.token.example.config.TokenAuthentication;

import java.security.MessageDigest;
import java.security.Principal;

public class SecurityUtil {

    public static String sha256Crypt(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(string.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Long extractUserId(Principal principal) {
        return ((TokenAuthentication) principal).getPrincipal().getId();
    }


}
