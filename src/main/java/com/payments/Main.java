package com.payments;

import com.payments.controller.PasswordEncryption;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Customer;
import com.payments.entety.Payment;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(PasswordEncryption.encryptPasswordSha1("2"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


    }
}

