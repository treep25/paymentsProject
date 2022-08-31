package com.payments.database;

public abstract class SqlQuery {

    public static class Customer {
        public static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
        public static final String SELECT_CUSTOMERS_BY_LOGIN = "SELECT * FROM customer";
        public static final String INSERT_CUSTOMER = "INSERT INTO customer (first_name,second_name,login,password,phone) VALUES (?,?,?,?,?)";
        public static final String SELECT_CUSTOMER_LOGIN_AND_PASSWORD = "SELECT login,password FROM customer";
        public static final String SELECT_CUSTOMER_LOGIN = "SELECT user_id FROM customer WHERE login = ?";
        public static final String SELECT_CUSTOMER_PHONE = "SELECT phone FROM customer";
        public static final String SELECT_CUSTOMER_LOGIN_Id = "SELECT login, user_id FROM customer";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_ID_EARLIER = "SELECT (user_id) FROM customer ORDER BY user_id LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_ID_LATEST = "SELECT (user_id) FROM customer ORDER BY  user_id DESC LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_NAME_EARLIER = "SELECT (user_id) FROM customer ORDER BY first_name LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_NAME_LATEST = "SELECT (user_id) FROM customer ORDER BY first_name DESC LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_AMOUNT_EARLIER = "SELECT (user_id) FROM card ORDER BY balance LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_AMOUNT_LATEST = "SELECT (user_id) FROM card ORDER BY balance DESC LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_3_TABLES = "SELECT * FROM customer,user_role,card WHERE customer.user_id = ? AND user_role.user_id=? AND card.user_id=?";
        public static final String ALL_CUSTOMERS_WHERE_STATUS_PREPARE = "SELECT * FROM card WHERE status='Prepare'";
        public static final String IS_EXIST_BY_ID = "SELECT * FROM customer WHERE user_id=?";
        public static final String REMOVE_CUSTOMER = "DELETE FROM customer WHERE user_id = ?";
        public static final String SET_PASSWORD = "UPDATE customer SET password = ? WHERE login = ?";


    }
    public static class Card {

    }
    public static class Payment {
        public static final String PAGINATION_ALL_PAYMENTS_EARLIER = "SELECT * FROM payment WHERE user_id = ? ORDER BY payment_id LIMIT ?, ?";
        public static final String PAGINATION_ALL_PAYMENTS_BY_DATE_EARLIER = "SELECT * FROM payment WHERE user_id = ? ORDER BY date LIMIT ?,?";
        public static final String PAGINATION_ALL_PAYMENTS_LATEST = "SELECT * FROM payment WHERE user_id = ? ORDER BY payment_id DESC LIMIT ?, ?";
        public static final String PAGINATION_ALL_PAYMENTS_BY_DATE_LATEST = "SELECT * FROM payment WHERE user_id = ? ORDER BY date DESC LIMIT ?,?";
        public static final String COUNT_RAWS_PAYMENTS_BY_USER = "SELECT COUNT(user_id) FROM payment WHERE user_id = ?";

    }
    public static class PaymentStatus {


    }
    public static class UserRole {
        public static final String INSERT_ROLE_USER = "INSERT INTO user_role (user_id,user_role) VALUES (?,?)";
        public static final String SELECT_ROLE_USER = "SELECT * FROM user_role";
    }
}
