package com.payments.controller;

import com.payments.database.DAO.CardDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {

    private static final Logger log = LoggerFactory.getLogger(PasswordEncryption.class);

    public static String encryptPasswordSha1(String password) throws UnsupportedEncodingException {

        MessageDigest sha = null;

        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        byte[] byteArray = password.getBytes(StandardCharsets.UTF_8);

        byte[] md5Bytes = sha.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (byte md5Byte : md5Bytes) {

            int val = ((int) md5Byte) & 0xff;

            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
