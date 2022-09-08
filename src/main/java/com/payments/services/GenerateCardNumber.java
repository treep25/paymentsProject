package com.payments.services;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateCardNumber {

    public static String generateCardNumber(){
        String cardNumber = ("4441 ");
        for (int i = 1; i < 13 ; i++) {
            cardNumber+= new Random().nextInt(10);
            if(i%4  == 0){
                cardNumber+=" ";
            }
        }
        return cardNumber.trim();
    }
}
