package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.entety.Card;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MakeRequest", value = "/MakeRequest")
public class MakeRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("requestError");

        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CardDAO cardDAO = new CardDAO(connectionPool);

        Card card = (Card) request.getSession().getAttribute("card");
        cardDAO.setCardStatus(card.getCardId(),"Prepare");
        request.getSession().setAttribute("requestError","requeest.error");
        response.sendRedirect("/personalCustomerAccount.jsp");
    }
}
