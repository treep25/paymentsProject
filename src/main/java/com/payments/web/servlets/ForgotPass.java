package com.payments.web.servlets;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("error");
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        try{
            if(CustomerDAO.getInstance().searchingByLogin(login)){
                CustomerDAO.getInstance().setPassword(login,password);
                response.sendRedirect("http://localhost:8080/signUp.jsp");
            }else {
                request.getSession().setAttribute("error","Customer isn`t exist");
                request.getRequestDispatcher("forgotPass.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
