package com.payments.entety;
import java.sql.Date;
import java.sql.Timestamp;

public class Payment {

    private int paymentId;
    private int userId;
    private Timestamp date;
    private String paymentStatus;



    public Payment(int paymentId,
                   String paymentStatus,
                   int userId, Timestamp date) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.userId = userId;
        this.date = date;
    }
    public Payment(int userId, Timestamp date,String paymentStatus) {
        this.userId = userId;
        this.date = date;
        this.paymentStatus = paymentStatus;
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
        return
                "paymentId =" + paymentId +
                ", paymentStatus=" + paymentStatus +
                ", userId=" + userId +
                ", data='" + date ;
    }
}
