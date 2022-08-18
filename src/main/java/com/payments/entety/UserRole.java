package com.payments.entety;

public class UserRole {
    private int userId;
    private String userRole;

    public UserRole(int userId, String userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public UserRole setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public UserRole setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }
}
