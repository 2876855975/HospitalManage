package com.accp.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class aaa {
    public static void main(String[] args) {
        System.out.println(new TokenProcessor().generateToken());
    }

}
