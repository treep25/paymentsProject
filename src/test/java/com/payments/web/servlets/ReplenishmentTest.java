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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReplenishmentTest {
    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    HttpSession session = mock(HttpSession.class);
    private final Card existingCard = new Card();
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
    void doGetTestTrue() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        int amount = 100;
        existingCard.setCardId(74);
        existingCard.setUserId(1);

        when(session.getAttribute("card")).thenReturn(existingCard);
        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));

        Replenishment replenishment = new Replenishment();
        replenishment.doPost(request,response);

        verify(response).sendRedirect("http://localhost:8080/cards.jsp");
    }

    @Test
    void doGetTestErrorAmountLessZero() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        int amount = -12;
        existingCard.setCardId(74);
        existingCard.setUserId(1);

        when(session.getAttribute("card")).thenReturn(existingCard);
        when(request.getParameter("amount")).thenReturn(String.valueOf(amount));

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("replenishment.jsp")).thenReturn(requestDispatcher);

        Replenishment replenishment = new Replenishment();
        replenishment.doPost(request,response);

        verify(requestDispatcher).forward(request,response);
    }
}