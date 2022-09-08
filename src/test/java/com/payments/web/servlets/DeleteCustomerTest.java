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

class DeleteCustomerTest {

    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    private final int existingId = 1;
    private final int notExistingId = 123;

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

        when(request.getParameter("id")).thenReturn(String.valueOf(existingId));

        when(session.getAttribute("customerId")).thenReturn(notExistingId);

        DeleteCustomer deleteCustomer = new DeleteCustomer();
        deleteCustomer.doPost(request,response);

        verify(response).sendRedirect("/PaginationAllCustomers?records=5&page=1&sorting=1");

    }

    @Test
    void doPostTestIsNotExist() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);

        ConnectionPool mockConnectionPool = mock(ConnectionPool.class);
        when(mockConnectionPool.getConnection()).thenReturn(con);

        when(servletContext.getAttribute("connectionPool")).thenReturn(mockConnectionPool);

        when(request.getParameter("id")).thenReturn(String.valueOf(notExistingId));
        when(session.getAttribute("customerId")).thenReturn(notExistingId);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("removeCustomer.jsp")).thenReturn(requestDispatcher);

        DeleteCustomer deleteCustomer = new DeleteCustomer();
        deleteCustomer.doPost(request,response);

        verify(requestDispatcher).forward(request,response);

    }
}