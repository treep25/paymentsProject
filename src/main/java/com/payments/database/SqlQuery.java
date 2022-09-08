package com.payments.database;

public abstract class SqlQuery {

    public static class Customer {
        public static final String SELECT_CUSTOMERS_BY_LOGIN = "SELECT * FROM customer";
        public static final String INSERT_CUSTOMER = "INSERT INTO customer (first_name,second_name,login,password,phone) VALUES (?,?,?,?,?)";
        public static final String SELECT_CUSTOMER_LOGIN_AND_PASSWORD = "SELECT login,password FROM customer";
        public static final String SELECT_CUSTOMER_LOGIN = "SELECT user_id FROM customer WHERE login = ?";
        public static final String SELECT_CUSTOMER_PHONE = "SELECT phone FROM customer";
        public static final String SELECT_CUSTOMER_LOGIN_Id = "SELECT login, user_id FROM customer";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_ID_EARLIER = "SELECT customer.user_id,customer.first_name,customer.second_name,customer.login,customer.phone,cards.card_number,cards.balance,cards.Status,user_role.user_role\n" +
                "FROM customer,cards,user_role WHERE customer.user_id =cards.user_id AND customer.user_id=user_role.user_id ORDER BY user_id DESC  LIMIT ?, ? ";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_ID_LATEST = "SELECT customer.user_id,customer.first_name,customer.second_name,customer.login,customer.phone,cards.card_number,cards.balance,cards.Status,user_role.user_role\\n\" +\n" +
                "                \"FROM customer,cards,user_role WHERE customer.user_id =cards.user_id AND customer.user_id=user_role.user_id ORDER BY user_id   LIMIT ?, ? ";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_NAME_EARLIER = "SELECT customer.user_id,customer.first_name,customer.second_name,customer.login,customer.phone,cards.card_number,cards.balance,cards.Status,user_role.user_role\n" +
                "FROM customer,cards,user_role WHERE customer.user_id =cards.user_id AND customer.user_id=user_role.user_id ORDER BY CHAR_LENGTH(first_name)  DESC  LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_NAME_LATEST = "SELECT customer.user_id,customer.first_name,customer.second_name,customer.login,customer.phone,cards.card_number,cards.balance,cards.Status,user_role.user_role\n" +
                "FROM customer,cards,user_role WHERE customer.user_id =cards.user_id AND customer.user_id=user_role.user_id ORDER BY CHAR_LENGTH(first_name)  LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_AMOUNT_EARLIER = "SELECT customer.user_id,customer.first_name,customer.second_name,customer.login,customer.phone,cards.card_number,cards.balance,cards.Status,user_role.user_role\n" +
                "FROM customer,cards,user_role WHERE customer.user_id =cards.user_id AND customer.user_id=user_role.user_id ORDER BY cards.balance DESC LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_BY_AMOUNT_LATEST = "SELECT customer.user_id,customer.first_name,customer.second_name,customer.login,customer.phone,cards.card_number,cards.balance,cards.Status,user_role.user_role\n" +
                "FROM customer,cards,user_role WHERE customer.user_id =cards.user_id AND customer.user_id=user_role.user_id ORDER BY cards.balance  LIMIT ?, ?";
        public static final String PAGINATION_ALL_CUSTOMERS_WHERE_STATUS_PREPARE= "SELECT customer.user_id,customer.first_name,customer.second_name,customer.login,customer.phone,cards.card_number,cards.balance,cards.Status,user_role.user_role\n" +
                "FROM customer,cards,user_role WHERE customer.user_id =cards.user_id AND customer.user_id=user_role.user_id AND cards.Status = 'Prepare'";
        public static final String IS_EXIST_BY_ID = "SELECT * FROM customer WHERE user_id=?";
        public static final String REMOVE_CUSTOMER = "DELETE FROM customer WHERE user_id = ?";
        public static final String SET_PASSWORD = "UPDATE customer SET password = ? WHERE login = ?";
        public static final String COUNT_NUM_OF_ROWS = "SELECT COUNT(cards.card_id) FROM cards,user_role WHERE cards.user_id = user_role.user_id AND user_role.user_role = 'Customer'";
    }
    public static class Card {
        public static final String UPDATE_CARD_SET_BALANCE_SENDER = "UPDATE cards SET balance = balance - ? WHERE card_number =?";
        public static final String UPDATE_CARD_SET_BALANCE_RECIPIENT = "UPDATE cards SET balance = balance + ? WHERE card_number =?";
        public static final String CREATE_CARD = "INSERT INTO cards(user_id,card_number) VALUES(?,?)";
        public static final String UPDATE_STATUS_BY_CARD_NUM = "UPDATE cards SET Status = ? WHERE card_number =?";
        public static final String GET_CARD_BY_CUSTOMER_ID ="SELECT * from cards WHERE user_id=?" ;
        public static final String IS_CARD_EXIST ="SELECT * FROM cards WHERE card_number=?" ;
        public static final String UPDATE_BALANCE_BY_CARD_NUMBER ="UPDATE cards SET balance = balance + ? WHERE card_number =?" ;
        public static final String GET_CARD_STATUS ="SELECT Status FROM cards WHERE card_number = ?" ;

    }
    public static class Payment {
        public static final String PAGINATION_ALL_PAYMENTS_EARLIER = "SELECT * FROM payment WHERE user_id = ? ORDER BY payment_id LIMIT ?, ?";
        public static final String PAGINATION_ALL_PAYMENTS_BY_DATE_EARLIER = "SELECT * FROM payment WHERE user_id = ? ORDER BY date LIMIT ?,?";
        public static final String PAGINATION_ALL_PAYMENTS_LATEST = "SELECT * FROM payment WHERE user_id = ? ORDER BY payment_id DESC LIMIT ?, ?";
        public static final String PAGINATION_ALL_PAYMENTS_BY_DATE_LATEST = "SELECT * FROM payment WHERE user_id = ? ORDER BY date DESC LIMIT ?,?";
        public static final String COUNT_RAWS_PAYMENTS_BY_USER = "SELECT COUNT(user_id) FROM payment WHERE user_id = ?";
        public static final String INSERT_INTO_PAYMENTS_ALL = "INSERT INTO payment VALUES (DEFAULT,?,?,?,?,?,?)";

    }
    public static class UserRole {
        public static final String INSERT_ROLE_USER = "INSERT INTO user_role (user_id,user_role) VALUES (?,?)";
        public static final String SELECT_ALL_WHERE_ID = "SELECT * FROM user_role WHERE user_id = ? ";
    }
}
