package com.payments;

import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Payment;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List <Payment> list;
        try {
            list = PaymentDAO.getInstance().getAllPaymentsByCustomerId(78,1,1,5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list);

        System.out.println(list);
        for (Payment pay:
             list) {


        }
    }
}

