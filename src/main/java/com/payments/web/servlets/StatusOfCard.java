package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.entety.Card;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "StatusOfCard", value = "/StatusOfCard")
public class StatusOfCard extends HttpServlet {

    private String numberOfCard;
    private String status;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        status = request.getParameter("status");
        numberOfCard = (request.getParameter("id"));
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CardDAO cardDAO = new CardDAO(connectionPool);
        cardDAO.setCardStatus(numberOfCard, status);
        request.getRequestDispatcher("PaginationAllCustomers?records=5&page=1&sorting=1").forward(request, response);
        }

}
