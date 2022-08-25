package com.payments.controller;

import com.payments.entety.Customer;

import java.util.Objects;
import java.util.regex.Pattern;

public class ValidationForms {

    public static boolean validateCustomer(Customer customer, String passRep) {
        boolean isTrue = true;
        if (customer.getFirstName() == null ||
                customer.getSecondName() == null ||
                customer.getLogin() == null ||
                customer.getPassword() == null ||
                customer.getPhone() == null) {
            isTrue = false;
        }

        if (!Pattern.matches("(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))", customer.getLogin()))
            isTrue = false;
        if (!Pattern.matches("(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?", customer.getPhone()))
            isTrue = false;
        if (!Pattern.matches("[0-9a-zA-Z!@#$%^&*]{6,}", customer.getPassword()))
            isTrue = false;
        if (!Pattern.matches("[0-9a-zA-Z!@#$%^&*]{6,}", passRep))
            isTrue = false;
        if(Objects.equals(customer.getPassword(), passRep)) isTrue = false;
        return isTrue;
    }
}
