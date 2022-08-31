package com.payments.services;

import com.payments.entety.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationFormsTest {
    private List<Customer> list = List.of(
            new Customer("Bogdan",
                    "Grigorenko",
                    "bo@gmail.com",
                    "123123qweqwe",
                    "+380-234-234-54")
            ,new Customer(null,
                    null,
                    "bo@gmail.com",
                    "123123qweqwe",
                    "+380-234-234-54")
            ,new Customer("Bogdan",
                    "Grigorenko",
                    "bogmail.com",
                    "123123qweqwe",
                    "+380-234-234-54")
            ,new Customer("Bogdan",
                    "Grigorenko",
                    "bogmail.com",
                    "123123",
                    "+380-234-234-54"));
    private List<String> list1 = List.of( "123123qweqwe","123123qweqwe", "123123");

    @Test
    void validateCustomerIfCorrect() {
        assertTrue(ValidationForms.validateCustomer(list.get(0),list1.get(0)));
    }
    @Test
    void validateCustomerInputsNull() {
        assertFalse(ValidationForms.validateCustomer(list.get(1),list1.get(1)));
    }
    @Test
    void validateCustomerLoginIncorrectAndPass() {
        assertTrue(ValidationForms.validateCustomer(list.get(0),list1.get(0)));
    }

}