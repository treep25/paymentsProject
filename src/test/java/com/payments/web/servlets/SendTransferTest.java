package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.entety.Card;
import com.payments.entety.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SendTransferTest {

    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    HttpSession session = mock(HttpSession.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    ServletContext servletContext = mock(ServletContext.class);
    private SendTransfer sendTransfer = new SendTransfer();

    @BeforeEach
    public void beforeStart() throws SQLException {
        con.setAutoCommit(false);
    }
    @AfterEach
    public void afterStart() throws SQLException {
        con.rollback();
    }

    @Test
    void getDate() {
        assertEquals(sendTransfer.getDate(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

    @Test
    void doGetTestSendStatus() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        String emailRecipient ="testCardDao@1gmail.com";
        int amount = 100;
        Card card = new Card();
        card.setBalance(1000);
        Customer customer = new Customer();
        customer.setLogin("testCardDao@gmail.com");
        customer.setUserID(1);
        customer.setPhone("123-456-78-90");
        customer.setFirstName("testCardDao");
        customer.setSecondName("testCardDao");

        when(request.getParameter("recipient")).thenReturn(emailRecipient);
        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));
        when(session.getAttribute("card")).thenReturn(card);
        when(session.getAttribute("customer")).thenReturn(customer);

        sendTransfer.doPost(request,response);

        verify(response).sendRedirect("http://localhost:8080/cards.jsp"); //TODO wrong with autocomitTEST

    }

    @Test
    void doGetTestErrorEmailExisting() {

    }

    @Test
    void doGetTestPrepareStatus() {
    }
}