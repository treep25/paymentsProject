package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.entety.Card;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Replenishment", value = "/Replenishment")
public class Replenishment extends HttpServlet {
    private CardDAO cardDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cardNumber = Integer.parseInt(request.getParameter("card"));
        request.getSession().setAttribute("cardNumber",cardNumber);
        response.sendRedirect("/replenishment.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Card> cardList = (List<Card>) request.getSession().getAttribute("cards");
        int cardNumber = (int) request.getSession().getAttribute("cardNumber");
        int amount = Integer.parseInt(request.getParameter("amount"));

        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        cardDAO = new CardDAO(connectionPool);

        if (amount > 0) {
            if(cardDAO.updateBalanceByCardNumber(cardList.get(cardNumber-1).getNumberOfCard(),amount)){
                cardList.get(cardNumber-1).setBalance(cardList.get(cardNumber-1).getBalance()+amount);
            }
            request.getSession().setAttribute("cards",cardList);
            response.sendRedirect("/cards.jsp");
        }else{
            request.getRequestDispatcher("replenishment.jsp").forward(request,response);
        }
    }
}
