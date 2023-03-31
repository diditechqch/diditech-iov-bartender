package com.iov.gps.auth.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void password() {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }

}
