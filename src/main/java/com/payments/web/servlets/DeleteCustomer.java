package com.payments.web.servlets;

import com.payments.database.DAO.CustomerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteCustomer", value = "/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("warning");
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            if(CustomerDAO.getInstance().isExist(id) && !request.getSession().getAttribute("customerId").equals(id)){
                CustomerDAO.getInstance().deleteCustomer(id);
                response.sendRedirect("http://localhost:8080/PaginationAllCustomers?records=5&page=1&sorting=1");
            }else {
                request.getSession().setAttribute("warning","That customer is not exist");
                request.getRequestDispatcher("removeCustomer.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
