package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.entety.Customer;
import com.payments.entety.Payment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Requests", value = "/Requests")
public class Requests extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = (ConnectionPool) getServletContext().getAttribute("connectionPool");
        CustomerDAO customerDAO = new CustomerDAO(connectionPool);
        List<Customer> list;
        list = customerDAO.getAllCustomerWhereCardStatusPrepare();
        request.getSession().setAttribute("requestList",list);
        response.sendRedirect("/requests.jsp");
    }


}
