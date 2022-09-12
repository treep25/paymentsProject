package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.services.PasswordEncryption;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.UserRoleDAO;
import com.payments.entety.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import static com.payments.services.ValidationForms.validateCustomer;

@WebServlet(name = "AddNewCustomer", value = "/AddNewCustomer")
public class AddNewCustomer extends HttpServlet {
    private CustomerDAO customerDAO;
    private CardDAO cardDAO;
    private UserRoleDAO userRoleDAO;
    private static final Logger log = LoggerFactory.getLogger(AddNewCustomer.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("error");
        request.getSession().removeAttribute("validationError");


        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        customerDAO = new CustomerDAO(connectionPool);
        cardDAO = new CardDAO(connectionPool);
        userRoleDAO = new UserRoleDAO(connectionPool);

        Customer customer = new Customer(request.getParameter("name"),
                request.getParameter("secName"),
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("phone"));

        String passwordRep = request.getParameter("passwordRepeat");
        if (!customerDAO.searchingByLogin(customer.getLogin())
                && !customerDAO.searchingByPhone(customer.getPhone())) {
            if (validateCustomer(customer, passwordRep)) {
                customer.setPassword(PasswordEncryption.encryptPasswordSha1(customer.getPassword()));

                customerDAO.addCustomer(customer);
                int id = customerDAO.getCustomerId(customer);

                customer.setUserID(id);
                cardDAO.creatCard(id);
                userRoleDAO.SetCustomerRoleByID4Customer(customer.getUserID());

                response.sendRedirect("/signUp.jsp");

            } else {
                log.error("Error in validation due to registration");
                request.getSession().setAttribute("validationError", "validationError");
                request.getRequestDispatcher("logIn.jsp").forward(request, response);
            }
        }
        else {
            log.error("Email or pass incorrect");
            request.getSession().setAttribute("error", "error");
            request.getRequestDispatcher("logIn.jsp").forward(request, response);
        }
    }
}



