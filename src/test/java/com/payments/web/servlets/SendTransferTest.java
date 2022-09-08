package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.entety.Card;
import com.payments.entety.Customer;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SendTransferTest {

    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    HttpSession session = mock(HttpSession.class);
    private String numberOfSenderExisting ="9999 9999 9999 9999";
    private String numberOfRecipientExisting = "9999 9999 9999 9991";
    private String numberOfRecipientNotExisting ="1234 9999 99qw99 9999";
    private int  userId =1;
    private int  cardNumber =1;
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
        con.setAutoCommit(false);
        con.rollback();
    }

    @Test
    void getDate() {
        assertEquals(sendTransfer.getDate(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

    @Test
    void doPostTestSendStatus() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        int amount = 100;
        int customerId = 1;
        Card card = new Card();
        card.setNumberOfCard(numberOfSenderExisting);
        card.setBalance(1000);
        List <Card> list = new LinkedList<>();
        list.add(card);

        when(request.getParameter("recipient")).thenReturn(numberOfRecipientExisting);
        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));
        when(session.getAttribute("customerId")).thenReturn(customerId);
        when(session.getAttribute("cardNumber")).thenReturn(1);
        when(session.getAttribute("cards")).thenReturn(list);

        sendTransfer.doPost(request,response);

        verify(response).sendRedirect("/cards.jsp");

    }

    @Test
    void doPostTestErrorCardNumberExisting() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        int amount = 100;
        int customerId = 1;
        Card card = new Card();
        card.setNumberOfCard(numberOfSenderExisting);
        card.setBalance(1000);
        List <Card> list = new LinkedList<>();
        list.add(card);
        when(request.getParameter("recipient")).thenReturn(numberOfRecipientNotExisting);
        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));
        when(session.getAttribute("customerId")).thenReturn(customerId);
        when(session.getAttribute("cardNumber")).thenReturn(1);
        when(session.getAttribute("cards")).thenReturn(list);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("send.jsp")).thenReturn(requestDispatcher);

        sendTransfer.doPost(request,response);

        verify(requestDispatcher).forward(request,response);
    }

}