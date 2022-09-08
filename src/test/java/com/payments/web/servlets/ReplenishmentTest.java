package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.entety.Card;
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
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReplenishmentTest {
    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    HttpSession session = mock(HttpSession.class);
    private  Card card = new Card();
    private int cardPos =1;
    private final List<Card> list = new LinkedList<>();
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
    void doGet()throws ServletException, IOException{
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);
        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);
        when(request.getParameter("card")).thenReturn(String.valueOf(cardPos));

        Replenishment replenishment = new Replenishment();
        replenishment.doGet(request,response);
        verify(response).sendRedirect("/replenishment.jsp");
    }
    @Test
    void doPostTestTrue() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);
        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        int amount = 100;
        card.setNumberOfCard("9999 9999 9999 9999");
        list.add(card);


        when(session.getAttribute("cards")).thenReturn(list);
        when(session.getAttribute("cardNumber")).thenReturn(cardPos);
        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));

        Replenishment replenishment = new Replenishment();
        replenishment.doPost(request,response);

        verify(response).sendRedirect("/cards.jsp");
    }

    @Test
    void doGetTestErrorAmountLessZero() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);
        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);
        int amount = -12;
        card.setNumberOfCard("9999 9999 9999 9999");
        list.add(card);

        when(session.getAttribute("cards")).thenReturn(list);
        when(session.getAttribute("cardNumber")).thenReturn(cardPos);
        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("replenishment.jsp")).thenReturn(requestDispatcher);

        Replenishment replenishment = new Replenishment();
        replenishment.doPost(request,response);

        verify(requestDispatcher).forward(request,response);
    }
}