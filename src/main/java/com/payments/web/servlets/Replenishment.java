package com.payments.web.servlets;

import com.payments.database.DAO.CardDAO;
import com.payments.entety.Card;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Replenishment", value = "/Replenishment")
public class Replenishment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        int balance = Integer.parseInt(request.getParameter("cardValue"));
        System.out.println(balance);
        if(balance >0){
            Card card = new Card();
            card = (Card) request.getSession().getAttribute("card");

            CardDAO.getInstance().updateBalanceByCardId(card.getCardId(),balance);
            card = CardDAO.getInstance().getCardById((Integer) request.getSession().getAttribute("customerId"));

            request.getSession().setAttribute("card",card);


            RequestDispatcher requestDispatcher = request.getRequestDispatcher("cards.jsp");
            requestDispatcher.forward(request,response);

        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("replenishment.jsp");
        requestDispatcher.forward(request,response);
    }
}
