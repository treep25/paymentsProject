package com.payments.entety;
import java.sql.Date;
import java.sql.Timestamp;

public class Payment {

    private int paymentId;
    private int userId;
    private String emailSender;
    private String emailRecipient;
    private Integer amount;
    private Timestamp date;
    private String paymentStatus;


    public Payment(int userId, String emailSender, String emailRecipient, Integer amount, Timestamp date, String paymentStatus) {
        this.userId = userId;
        this.emailSender = emailSender;
        this.emailRecipient = emailRecipient;
        this.amount = amount;
        this.date = date;
        this.paymentStatus = paymentStatus;
    }

    public Payment(int paymentId, int userId, String emailSender,
                   String emailRecipient, Integer amount, Timestamp date, String paymentStatus) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.emailSender = emailSender;
        this.emailRecipient = emailRecipient;
        this.amount = amount;
        this.date = date;
        this.paymentStatus = paymentStatus;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public Payment setEmailSender(String emailSender) {
        this.emailSender = emailSender;
        return this;
    }

    public String getEmailRecipient() {
        return emailRecipient;
    }

    public Payment setEmailRecipient(String emailRecipient) {
        this.emailRecipient = emailRecipient;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public Payment setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public int getUserId() {
        return userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  "Sender: " + emailSender +
                " Recipient: " + emailRecipient +
                " Amount: " + amount +
                " date: " + date +
                " Status: " + paymentStatus;
    }
}
