package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Customer;
import com.payments.entety.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PaginationAllCustomers", value = "/PaginationAllCustomers")
public class PaginationAllCustomers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rows = 0;
        int sorting = Integer.parseInt(request.getParameter("sorting"));
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int recordsPerPage = Integer.parseInt(request.getParameter("records"));

        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CustomerDAO customerDAO = new CustomerDAO(connectionPool);

        List<Customer> list;
        list = customerDAO.getAllCustomers(sorting, currentPage, recordsPerPage);
        request.getSession().setAttribute("listOfCustomers", list);

        rows = customerDAO.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) nOfPages++;

        request.getSession().setAttribute("sorting", sorting);
        request.getSession().setAttribute("noOfPages", nOfPages);
        request.getSession().setAttribute("currentPage", currentPage);
        request.getSession().setAttribute("recordsPerPage", recordsPerPage);
        response.sendRedirect("/customers.jsp");
    }

}
