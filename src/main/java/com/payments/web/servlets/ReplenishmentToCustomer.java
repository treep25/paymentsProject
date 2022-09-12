package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReplenishmentToCustomer", value = "/ReplenishmentToCustomer")
public class ReplenishmentToCustomer extends HttpServlet {

    private CardDAO cardDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("error");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String customerNumberCard = request.getParameter("recipient");
        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        cardDAO = new CardDAO(connectionPool);
        if (amount > 0) {
            if (cardDAO.isCardExist(customerNumberCard) && cardDAO.getStatusOfCard(customerNumberCard).equals("Active")) {
                cardDAO.updateBalanceByCardNumber(customerNumberCard, amount);
                response.sendRedirect("/topUp.jsp");
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
