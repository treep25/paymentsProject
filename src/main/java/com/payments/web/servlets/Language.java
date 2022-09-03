package com.payments.web.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Language", value = "/Language")
public class Language extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String language =  request.getParameter("lan");
       request.getSession().setAttribute("locale",language);
       doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/personalCustomerAccount.jsp");
    }
}
