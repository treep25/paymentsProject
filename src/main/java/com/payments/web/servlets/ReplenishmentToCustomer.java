package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.entety.Card;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ReplenishmentToCustomer", value = "/ReplenishmentToCustomer")
public class ReplenishmentToCustomer extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("error");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int customerId = Integer.parseInt(request.getParameter("recipient"));

        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CardDAO cardDAO = new CardDAO(connectionPool);

        if (amount > 0) {
            Card card = cardDAO.getCardById(customerId);
            if (cardDAO.isCardExist(card.getCardId())) {
                cardDAO.updateBalanceByCardId(card.getCardId(), amount);
                response.sendRedirect("http://localhost:8080/topUp.jsp");
            } else {
                request.getSession().setAttribute("error", "card.isn`t.exist");
                request.getRequestDispatcher("topUp.jsp").forward(request, response);
            }
        } else {
            request.getSession().setAttribute("error", "incorrect.amount");
            request.getRequestDispatcher("topUp.jsp").forward(request, response);
        }

    }
}
