package com.payments;

import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Payment;

import java.sql.*;
import java.util.Date;


public class Main {
    public static void main(String[] args) {

        System.out.println(CardDAO.getInstance().getCardIdByUserId(77));


    }
}

