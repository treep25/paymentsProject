package com.payments.web.servlets;

import com.payments.controller.ValidationForms;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.UserRoleDAO;
import com.payments.entety.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.payments.controller.ValidationForms.validateCustomer;

@WebServlet(name = "AddNewCustomer", value = "/AddNewCustomer")
public class AddNewCustomer extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Customer customer = new Customer(request.getParameter("name"),
                    request.getParameter("secName"),
                    request.getParameter("email"),
                    request.getParameter("password"),
                    request.getParameter("phone"));
            String passwordRep = request.getParameter("passwordRepeat");

            if(validateCustomer(customer,passwordRep)){
                CustomerDAO.getInstance().addCustomer(customer);
                int id  = CustomerDAO.getInstance().getCustomerId(customer);
                customer.setUserID(id);
                CardDAO.getInstance().creatCardForCustomer(id);
                UserRoleDAO.getInstance().SetCustomerRoleByID4Customer(customer.getUserID());

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("signUp.jsp");
                requestDispatcher.forward(request,response);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("logIn.jsp");
            requestDispatcher.forward(request,response);
        }catch (SQLException e){
           throw new IllegalArgumentException();
        }


    }
}



