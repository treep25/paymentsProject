package com.payments.entety;

public class Card {

    private int cardId;
    private int userId;
    private int balance = 0;
    private String status ;

    public Card() {
    }

    public Card(int cardId, int userId, int balance) {
        this.cardId = cardId;
        this.userId = userId;
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public Card setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }

}
