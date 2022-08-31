package com.payments.web.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "LogOut", value = "/LogOut")
public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("card");
        request.getSession().removeAttribute("customerId");
        request.getSession().removeAttribute("customer");
        request.getSession().removeAttribute("warning");
        request.getSession().removeAttribute("listOfCustomers");
        request.getSession().removeAttribute("paymentList");
        request.getSession().removeAttribute("requestList");
        request.getSession().removeAttribute("locale");
        request.getSession().removeAttribute("error");
        request.getSession().removeAttribute("error1");
        response.sendRedirect("http://localhost:8080/mainPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
