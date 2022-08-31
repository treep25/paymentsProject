package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.SqlQuery;
import com.payments.entety.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ForgotPass", value = "/ForgotPass")
public class ForgotPass extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("errorForgotPass");
        String login = request.getParameter("email");
        String password = request.getParameter("password");

        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CustomerDAO customerDAO = new CustomerDAO(connectionPool);

        if(customerDAO.searchingByLogin(login)){
            customerDAO.setPassword(login,password);
            response.sendRedirect("http://localhost:8080/signUp.jsp");
        }else {
            request.getSession().setAttribute("errorForgotPass","forgot.password.session.error");
            request.getRequestDispatcher("forgotPass.jsp").forward(request,response);
        }
    }
}
