package com.payments.web.servlets;

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
        request.getSession().removeAttribute("warning");
        Card card = (Card) request.getSession().getAttribute("card");
        System.out.println(card.toString());
        System.out.println(card.getStatus());
        try {
            CardDAO.getInstance().setCardStatus(card.getCardId(),"Prepare");
            request.getSession().setAttribute("warning","You have already made request");
            response.sendRedirect("http://localhost:8080/personalCustomerAccount.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
