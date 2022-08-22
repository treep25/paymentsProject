package com.payments.web.servlets;

import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Payment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(name = "Pagination", value = "/Pagination")
public class Pagination extends HttpServlet {
    private Integer inverse (int number){
        return -number;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int rows = 0;
        int sorting = Integer.parseInt(request.getParameter("sorting"));
        int customerId = (Integer) request.getSession().getAttribute("customerId");
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int recordsPerPage = Integer.parseInt(request.getParameter("records"));

        List<Payment> list;
        try {
            list = PaymentDAO.getInstance().getAllPaymentsByCustomerId(customerId,sorting,currentPage,recordsPerPage);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("paymentList",list);
        try {
            rows = PaymentDAO.getInstance().getNumberOfRows(customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) nOfPages++;

        request.getSession().setAttribute("sorting", sorting);
        sorting = inverse(sorting);
        request.getSession().setAttribute("noOfPages", nOfPages);
        request.getSession().setAttribute("currentPage", currentPage);
        request.getSession().setAttribute("recordsPerPage", recordsPerPage);
        response.sendRedirect("http://localhost:8080/payments.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
