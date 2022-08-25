package com.payments.web.servlets;

import com.payments.controller.PasswordEncryption;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.UserRoleDAO;
import com.payments.database.SqlQuery;
import com.payments.entety.Card;
import com.payments.entety.Customer;
import com.payments.entety.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        request.getSession().removeAttribute("validationError");
        request.getSession().removeAttribute("error1");

        try {
            if (CustomerDAO.getInstance().searchingByLoginAndPassword(login
                    ,PasswordEncryption.encryptPasswordSha1(password))) {

                try {
                    Customer customer = CustomerDAO.getInstance().getCustomerByLogin(login);
                    customer.setPassword(null);
                    String userRole = UserRoleDAO.getInstance().showUserRoleById(customer.getUserID());

                    Card card = CardDAO.getInstance().getCardById(customer.getUserID());


                    request.getSession().setAttribute("customerId", customer.getUserID());
                    request.getSession().setAttribute("customer", customer);
                    request.getSession().setAttribute("card", card);
                    request.getSession().setAttribute("role", userRole);

                    response.sendRedirect("http://localhost:8080/personalCustomerAccount.jsp");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                request.getSession().setAttribute("error1","Login or password is incorrect");
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}



