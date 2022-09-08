package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.entety.Card;
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
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MakeRequestTest {
    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();

    private  Card card = new Card();
    private final List<Card> list = new LinkedList<>();
    private int cardPos =1;
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
    void doGetTest() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        card.setNumberOfCard("9999 9999 9999 9999");
        list.add(card);

        when(request.getParameter("card")).thenReturn(String.valueOf(cardPos));
        when(session.getAttribute("cards")).thenReturn(list);
        MakeRequest makeRequest = new MakeRequest();
        makeRequest.doGet(request,response);

        verify(response).sendRedirect("/cards.jsp");


    }
}