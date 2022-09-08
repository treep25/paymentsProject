package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.entety.Card;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MakeRequest", value = "/MakeRequest")
public class MakeRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("requestError");
        int number = Integer.parseInt(request.getParameter("card"));
        List<Card> cards = (List<Card>) request.getSession().getAttribute("cards");
        Card card = cards.get(number-1);
        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CardDAO cardDAO = new CardDAO(connectionPool);

        cardDAO.setCardStatus(card.getNumberOfCard(),"Prepare");
        cards.set(number-1, card);
        request.getSession().setAttribute("cards",cards);
        request.getSession().setAttribute("requestError","requeest.error");
        response.sendRedirect("/cards.jsp");

    }
}
