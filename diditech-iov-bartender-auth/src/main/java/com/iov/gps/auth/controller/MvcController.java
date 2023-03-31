package com.iov.gps.auth.controller;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

import lombok.AllArgsConstructor;

/**
 * 公开对称加密接口
 * @author hefan
 * @date 2020/8/18 10:23
 */
@RestController
@AllArgsConstructor
public class MvcController {

    private final KeyPair keyPair;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

    @GetMapping("/users/me")
    public Object getCurrentUserInfo(@AuthenticationPrincipal Object principal) {
        return Collections.singletonMap("username", principal);
    }
}
