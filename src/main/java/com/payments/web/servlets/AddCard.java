package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.entety.Card;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddCard", value = "/AddCard")
public class AddCard extends HttpServlet {
    private CardDAO cardDAO;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        int currentId = (int) request.getSession().getAttribute("customerId");
        cardDAO = new CardDAO(connectionPool);
        cardDAO.creatCard(currentId);
        List<Card> cards = cardDAO.getCardByCustomerId(currentId);
        request.getSession().setAttribute("cards", cards);

        response.sendRedirect("/cards.jsp");
    }
}
