package com.back.support;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptUtilsTest {

    @Test
    void encrypt() {
        String str = "brdnd";
        String encryptResult = CryptUtils.encrypt(str);
        System.out.println(">>"+encryptResult);
    }

    @Test
    void decrypt() {
        String str = "PdntrZk6+mVOhav3rFjmVw==";
        String decryptResult = CryptUtils.decrypt(str);
        System.out.println(">>"+decryptResult);
    }

    @Test
    void encryptSha256() throws NoSuchAlgorithmException {
        String str = "a123123!";
        String encryptSha256Result = CryptUtils.encryptSha256(str);
        System.out.println(">>"+encryptSha256Result);
    }

    @Value("${properties.test}")
    private String testValue;

    @Test
    void enc() {
        System.out.println("<<"+testValue);
    }
}
