package com.payments.web.servlets;

import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Payment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Payments", value = "/Payments")
public class Payments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> list;
        try {
            list = PaymentDAO.getInstance().getAllPaymentsByCustomerId((Integer) request.getSession()
                    .getAttribute("customerId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("paymentList",list);
        request.getRequestDispatcher("payments.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
