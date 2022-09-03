package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteCustomer", value = "/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("warning");

        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CustomerDAO customerDAO = new CustomerDAO(connectionPool);

        int id = Integer.parseInt(request.getParameter("id"));
        if(customerDAO.isExist(id) && !request.getSession().getAttribute("customerId").equals(id)){
            customerDAO.deleteCustomer(id);
            response.sendRedirect("/PaginationAllCustomers?records=5&page=1&sorting=1");
        }else {
            request.getSession().setAttribute("warning","this.customer.is.not.exist");
            request.getRequestDispatcher("removeCustomer.jsp").forward(request,response);
        }
    }
}
