package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

class AddNewCustomerTest {

    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
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
    void doPostTestTrue() throws ServletException, IOException{

        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("secName")).thenReturn("SecName");
        when(request.getParameter("email")).thenReturn("email@gmail.com");
        when(request.getParameter("phone")).thenReturn("+876-45-345-54");
        when(request.getParameter("password")).thenReturn("qwe123!");
        when(request.getParameter("passwordRepeat")).thenReturn("qwe123!");

        AddNewCustomer addNewCustomer = new AddNewCustomer();
        addNewCustomer.doPost(request,response);

        verify(response).sendRedirect("/signUp.jsp");


    }
    @Test
    void doPostTestErrorValidate() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        when(request.getParameter("name")).thenReturn("123");
        when(request.getParameter("secName")).thenReturn("S123");
        when(request.getParameter("email")).thenReturn("email123@2131gmail.com");
        when(request.getParameter("phone")).thenReturn("+123-1-3-123wqewqeweq51234");
        when(request.getParameter("password")).thenReturn("q12123123!");
        when(request.getParameter("passwordRepeat")).thenReturn("q12323");

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("logIn.jsp")).thenReturn(requestDispatcher);

        AddNewCustomer addNewCustomer = new AddNewCustomer();
        addNewCustomer.doPost(request,response);

        verify(requestDispatcher).forward(request,response);
    }
    @Test
    void doPostTestErrorExistingAccount() throws ServletException, IOException {
        doPostTestTrue();

        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        when(request.getParameter("name")).thenReturn("SomeName");
        when(request.getParameter("secName")).thenReturn("SomeSecName");
        when(request.getParameter("email")).thenReturn("email@gmail.com");
        when(request.getParameter("phone")).thenReturn("+876-45-345-54");
        when(request.getParameter("password")).thenReturn("qwe123!");
        when(request.getParameter("passwordRepeat")).thenReturn("qwe123!");

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("logIn.jsp")).thenReturn(requestDispatcher);

        AddNewCustomer addNewCustomer = new AddNewCustomer();
        addNewCustomer.doPost(request,response);

        verify(requestDispatcher).forward(request,response);
    }
}