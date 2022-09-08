package com.payments.entety;

public class Payment {

    private int paymentId;
    private int userId;
    private String cardNumberSender;
    private String cardNumberRecipient;
    private Integer amount;
    private String date;
    private String paymentStatus;


    public Payment(int userId, String emailSender, String emailRecipient, Integer amount, String date, String paymentStatus) {
        this.userId = userId;
        this.cardNumberSender = emailSender;
        this.cardNumberRecipient = emailRecipient;
        this.amount = amount;
        this.date = date;
        this.paymentStatus = paymentStatus;
    }

    public Payment(int paymentId, int userId, String emailSender,
                   String emailRecipient, Integer amount, String date, String paymentStatus) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.cardNumberSender = emailSender;
        this.cardNumberRecipient = emailRecipient;
        this.amount = amount;
        this.date = date;
        this.paymentStatus = paymentStatus;
    }

    public String getCardNumberSender() {
        return cardNumberSender;
    }

    public Payment setCardNumberSender(String cardNumberSender) {
        this.cardNumberSender = cardNumberSender;
        return this;
    }

    public String getCardNumberRecipient() {
        return cardNumberRecipient;
    }

    public Payment setCardNumberRecipient(String cardNumberRecipient) {
        this.cardNumberRecipient = cardNumberRecipient;
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

    public String getDate() {
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

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  "Sender: " + cardNumberSender +
                " Recipient: " + cardNumberRecipient +
                " Amount: " + amount +"$"+
                " date: " + date +
                " Status: " + paymentStatus;
    }
}
