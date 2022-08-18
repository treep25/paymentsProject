package com.payments.web.servlets;

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
        try{
            if(CustomerDAO.getInstance().searchingByLoginAndPassword(login,password)){
                Customer customer = CustomerDAO.getInstance().getCustomerByLogin(login);
                customer.setPassword(null);
                String userRole = UserRoleDAO.getInstance().showUserRoleById(customer.getUserID());

                Card card = CardDAO.getInstance().getCardById(customer.getUserID());


                request.getSession().setAttribute("customerId",customer.getUserID());
                request.getSession().setAttribute("customer",customer);
                request.getSession().setAttribute("card",card);
                request.getSession().setAttribute("role",userRole);



                RequestDispatcher requestDispatcher = request.getRequestDispatcher("personalCustomerAccount.jsp");
                requestDispatcher.forward(request,response);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("signUp.jsp");
        requestDispatcher.forward(request,response);
    }
}



