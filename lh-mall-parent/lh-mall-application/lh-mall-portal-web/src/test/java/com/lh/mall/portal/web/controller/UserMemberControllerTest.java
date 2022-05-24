package com.lh.mall.portal.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserMemberControllerTest {
    @Test
    void test() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String s = "megn";
        String encode = passwordEncoder.encode(s);
        System.out.println(encode);
        System.out.println("=================================");
        System.out.println(passwordEncoder.matches(s, encode));
    }
}