package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Card;
import com.payments.entety.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@WebServlet(name = "SendTransfer", value = "/SendTransfer")
public class SendTransfer extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SendTransfer.class);
    private CardDAO cardDAO;
    private PaymentDAO paymentDAO;

    public String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cardNumber = Integer.parseInt(request.getParameter("card"));
        request.getSession().setAttribute("cardNumber", cardNumber);

        response.sendRedirect("/send.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("warning");
        String cardNumberRecipient = request.getParameter("recipient");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int idCustomer = (int) request.getSession().getAttribute("customerId");
        int cardNumber = (int) request.getSession().getAttribute("cardNumber");
        List<Card> cardList = (List<Card>) request.getSession().getAttribute("cards");
        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        cardDAO = new CardDAO(connectionPool);
        paymentDAO = new PaymentDAO(connectionPool);
        Card card = cardList.get(cardNumber - 1);

        if (!cardNumberRecipient.isEmpty() && cardDAO.isCardExist(cardNumberRecipient) && cardDAO.getStatusOfCard(cardNumberRecipient).equals("Active")
                && amount > 0 && card.getBalance() >= amount
                && !cardNumberRecipient.equals(card.getNumberOfCard())) {

            Payment payment = new Payment(idCustomer, card.getNumberOfCard(),
                    cardNumberRecipient, amount, getDate(), "Prepared");
            try {
                if (!cardDAO.transferP2P(card.getNumberOfCard(), cardNumberRecipient, amount)) {
                    paymentDAO.addPayment(payment);
                } else {
                    payment.setPaymentStatus("Send");
                    paymentDAO.addPayment(payment);
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
            for (Card c :
                    cardList) {
                if (c.getNumberOfCard().equals(cardNumberRecipient)) c.setBalance(c.getBalance() + amount);
            }
            card.setBalance(card.getBalance() - amount);
            cardList.set(cardNumber - 1, card);
            request.getSession().setAttribute("cards", cardList);
            response.sendRedirect("/cards.jsp");
        } else {
            request.getSession().setAttribute("warning", "email.has`t.existed.or.You.haven`t.enough.money");
            request.getRequestDispatcher("send.jsp").forward(request, response);
        }

    }

}
