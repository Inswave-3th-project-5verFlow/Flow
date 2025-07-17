package com.demo.proworks.cmmn;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.stereotype.Component;

import com.inswave.elfw.security.ElAbstractCrypto;

@Component("elSha256Crypto")
public class ElSha256Crypto extends ElAbstractCrypto {
    
    // 고정 salt (운영 환경에서는 사용자별로 동적 salt를 사용하는 것이 더 안전)
    private static final String SALT = "Proworks$Secure#Salt123";

    @Override
    public String getEncrypt(String cryptoKind, String plainText) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Salt를 plainText 앞뒤로 추가
            String saltedText = SALT + plainText + SALT;
            byte[] hash = digest.digest(saltedText.getBytes(StandardCharsets.UTF_8));

            // 바이트 배열을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 암호화 실패", e);
        }
    }

    @Override
    public String getDecrypt(String cryptoKind, String encryptedText) throws Exception {
        throw new UnsupportedOperationException("SHA-256은 복호화할 수 없습니다.");
    }	
}
