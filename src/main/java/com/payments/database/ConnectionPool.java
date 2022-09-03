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
    private String url ;
    private String username = "root";
    private String password = "806127059";


    public ConnectionPool(String url)  {
        this.url = url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            log.error(ex.getMessage());
            System.out.println("Everything is Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }



}
