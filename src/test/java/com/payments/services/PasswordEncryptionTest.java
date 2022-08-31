package com.payments.services;

import org.junit.jupiter.api.Test;

import static com.payments.services.PasswordEncryption.encryptPasswordSha1;
import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptionTest {

    @Test
    void encryptPasswordSha1Exception() {
        assertThrows(NullPointerException.class, () -> encryptPasswordSha1(null));
    }
    @Test
    void encryptPasswordSha1Correct1() {
        assertEquals("4f069612205493bd55fc1df59343e22a21561c8c",encryptPasswordSha1("!@#$%^&*()(*&^%$#@#$%^&*()_"));
    }
    @Test
    void encryptPasswordSha1ExceptionCorrect2() {
        assertEquals("09ac66829bfae1cedc9c7e8a2190df295cd9b00a",encryptPasswordSha1("ВЫАЛЫВЛАЫВАЩЫВШАЛ"));
    }
}