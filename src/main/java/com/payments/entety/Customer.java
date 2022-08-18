package com.payments.entety;

public class Customer {

    private int userID;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private String phone;


    public Customer() {
    }

    public Customer(int userID,
                    String firstName,
                    String secondName,
                    String login,
                    String password, String phone) {
        this.userID = userID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }
    public Customer(String firstName,
                    String secondName,
                    String login,
                    String password, String phone) {
        this.userID = userID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Customer setUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public Customer setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }


    public String getLogin() {
        return login;
    }

    public Customer setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Customer setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
