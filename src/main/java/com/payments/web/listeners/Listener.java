package com.payments.web.listeners;

import com.payments.database.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.net.ConnectException;
import java.sql.SQLException;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public Listener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        ServletContext servletContext = contextEvent.getServletContext();

        ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://127.0.0.1:3306/payments");
        servletContext.setAttribute("connectionPool", connectionPool);


    }


}
