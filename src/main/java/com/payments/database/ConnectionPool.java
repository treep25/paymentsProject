package com.payments.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private Connection connection;
    private String url = "jdbc:mysql://127.0.0.1:3306/payments";
    private String username = "root";
    private String password = "806127059";

    private ConnectionPool() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            log.error(ex.getMessage());
            System.out.println("Everything is Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static synchronized ConnectionPool getInstance() throws SQLException {
        if(instance!= null) return instance;
        instance = new ConnectionPool();
        return instance;
    }

}
