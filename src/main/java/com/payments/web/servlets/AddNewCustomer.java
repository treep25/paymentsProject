package com.payments.web.servlets;

import com.payments.controller.ValidationForms;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.UserRoleDAO;
import com.payments.entety.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.payments.controller.ValidationForms.validateCustomer;

@WebServlet(name = "AddNewCustomer", value = "/AddNewCustomer")
public class AddNewCustomer extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AddNewCustomer.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("error");
        request.getSession().removeAttribute("validationError");
        request.getSession().removeAttribute("error1");

        CustomerDAO customerDAO = CustomerDAO.getInstance();

        Customer customer = new Customer(request.getParameter("name"),
                request.getParameter("secName"),
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("phone"));

        String passwordRep = request.getParameter("passwordRepeat");
        if (!customerDAO.searchingByLogin(customer.getLogin())
                || !customerDAO.searchingByPhone(customer.getPhone())) {

            if (validateCustomer(customer, passwordRep)) {
                try {
                    CustomerDAO.getInstance().addCustomer(customer);
                    int id = CustomerDAO.getInstance().getCustomerId(customer);
                    customer.setUserID(id);
                    CardDAO.getInstance().creatCardForCustomer(id);
                    UserRoleDAO.getInstance().SetCustomerRoleByID4Customer(customer.getUserID());
                    response.sendRedirect("http://localhost:8080/signUp.jsp");

                } catch (SQLException e) {
                    log.error(e.getMessage());
                }
            } else {
                request.getSession().setAttribute("validationError", "Fields are not correct");
                request.getRequestDispatcher("logIn.jsp").forward(request, response);
            }
        }
        else {
            request.getSession().setAttribute("error", "This email or phone number has already exist");
            request.getRequestDispatcher("logIn.jsp").forward(request, response);
        }
    }
}



