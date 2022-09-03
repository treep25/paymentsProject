package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.PaymentDAO;
import com.payments.services.PasswordEncryption;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.UserRoleDAO;
import com.payments.entety.Card;
import com.payments.entety.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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


        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CustomerDAO customerDAO = new CustomerDAO(connectionPool);
        UserRoleDAO userRoleDAO = new UserRoleDAO(connectionPool);
        CardDAO cardDAO = new CardDAO(connectionPool);

        if (customerDAO.searchingByLoginAndPassword(login
                , PasswordEncryption.encryptPasswordSha1(password))) {

            Customer customer = customerDAO.getCustomerByLogin(login);
            customer.setPassword(null);
            String userRole = userRoleDAO.showUserRoleById(customer.getUserID());

            Card card = cardDAO.getCardById(customer.getUserID());


            request.getSession().setAttribute("customerId", customer.getUserID());
            request.getSession().setAttribute("customer", customer);
            request.getSession().setAttribute("card", card);
            request.getSession().setAttribute("role", userRole);
            request.getSession().setAttribute("locale", "en");

            response.sendRedirect("/personalCustomerAccount.jsp");

        } else {
            request.getSession().setAttribute("error1", "incorrect.login.or.pass");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        }


    }
}



