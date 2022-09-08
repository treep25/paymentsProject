package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReplenishmentToCustomerTest {

    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    private String numberOfRecipientExisting ="9999 9999 9999 9999";
    private String numberOfRecipientNotExisting ="1234 9999 99qw99 9999";
    HttpSession session = mock(HttpSession.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    ServletContext servletContext = mock(ServletContext.class);

    @BeforeEach
    public void beforeStart() throws SQLException {
        con.setAutoCommit(false);
    }
    @AfterEach
    public void afterStart() throws SQLException {
        con.rollback();
    }

    @Test
    void doPostTestTrue() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        int amount = 1000;


        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));
        when(request.getParameter("recipient")).thenReturn(String.valueOf(numberOfRecipientExisting));

        ReplenishmentToCustomer replenishmentToCustomer = new ReplenishmentToCustomer();
        replenishmentToCustomer.doPost(request,response);

        verify(response).sendRedirect("/topUp.jsp");

    }
    @Test
    void doPostTestCardError() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        int amount = 1000;

        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));
        when(request.getParameter("recipient")).thenReturn(String.valueOf(numberOfRecipientNotExisting));

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("topUp.jsp")).thenReturn(requestDispatcher);

        ReplenishmentToCustomer replenishmentToCustomer = new ReplenishmentToCustomer();
        replenishmentToCustomer.doPost(request, response);

        verify(requestDispatcher).forward(request,response);
    }
    @Test
    void doPostTestAmountError() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        int amount = -1000;

        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));
        when(request.getParameter("recipient")).thenReturn(String.valueOf(numberOfRecipientExisting));

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("topUp.jsp")).thenReturn(requestDispatcher);

        ReplenishmentToCustomer replenishmentToCustomer = new ReplenishmentToCustomer();
        replenishmentToCustomer.doPost(request, response);

        verify(requestDispatcher).forward(request,response);
    }
}