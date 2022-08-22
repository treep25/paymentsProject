package com.payments.web.servlets;

import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Payment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PaymentsPrevious", value = "/PaymentsPrevious")
public class PaymentsPrevious extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int limit = (int) request.getSession().getAttribute("limit");
//        limit-=10;
//        List<Payment> list;
//        try {
//            list = PaymentDAO.getInstance().getAllPaymentsByCustomerId((Integer) request.getSession()
//                    .getAttribute("customerId"),limit);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        request.getSession().setAttribute("paymentList",list);
//        request.getSession().setAttribute("limit",limit);
//        response.sendRedirect("http://localhost:8080/payments.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
