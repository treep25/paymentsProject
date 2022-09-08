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

class SignUpTest {
    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    private String emailExisting = "testCardDao@gmail.com";
    private String passExisting = "1";
    private String passNotExisting = "11";
    private String emailNotExisting = "testCardDao@gmail.com";

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
    void doGetTrue() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);
        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);
        when(request.getParameter("email")).thenReturn(emailExisting);
        when(request.getParameter("password")).thenReturn(passExisting);
        SignUp signUp = new SignUp();
        signUp.doGet(request,response);
        verify(response).sendRedirect("/personalCustomerAccount.jsp");

    }
    @Test
    void doGetErrorPassAndLogin() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);
        when(request.getParameter("email")).thenReturn(emailNotExisting);
        when(request.getParameter("password")).thenReturn(passNotExisting);
        SignUp signUp = new SignUp();

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("signUp.jsp")).thenReturn(requestDispatcher);
        signUp.doGet(request,response);
        verify(requestDispatcher).forward(request,response);

    }
    @Test
    void doGetErrorPass() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);
        when(request.getParameter("email")).thenReturn(emailExisting);
        when(request.getParameter("password")).thenReturn(passNotExisting);
        SignUp signUp = new SignUp();

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("signUp.jsp")).thenReturn(requestDispatcher);
        signUp.doGet(request,response);
        verify(requestDispatcher).forward(request,response);
    }
}