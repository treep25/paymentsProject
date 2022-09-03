package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.entety.Card;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Replenishment", value = "/Replenishment")
public class Replenishment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Card card = (Card) request.getSession().getAttribute("card");
        int balance = card.getBalance();
        int amount = Integer.parseInt(request.getParameter("amount"));
        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CardDAO cardDAO = new CardDAO(connectionPool);
        if (amount > 0) {
            if(cardDAO.updateBalanceByCardId(card.getCardId(),amount)){
                card.setBalance(balance + amount);
            }
            request.getSession().setAttribute("card",card);
            response.sendRedirect("/cards.jsp");
        }else{
            request.getRequestDispatcher("replenishment.jsp").forward(request,response);
        }
    }
}
