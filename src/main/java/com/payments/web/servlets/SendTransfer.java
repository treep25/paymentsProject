package com.payments.web.servlets;

import com.payments.database.DAO.CardDAO;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.DAO.PaymentDAO;
import com.payments.entety.Card;
import com.payments.entety.Customer;
import com.payments.entety.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet(name = "SendTransfer", value = "/SendTransfer")
public class SendTransfer extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SendTransfer.class);
    private CustomerDAO customerDAO ;
    private PaymentDAO paymentDAO ;
    private CardDAO cardDAO;

    public Timestamp getDate() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    // just CustomerDAO edit
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("emailToSend");
        int cardValue = Integer.parseInt(request.getParameter("cardValue"));
        Card card = (Card) request.getSession().getAttribute("card");
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customerDAO = CustomerDAO.getInstance();

        if (email.length() != 0 && cardValue > 0 && cardValue < card.getBalance()) {
            if (customerDAO.searchingByLogin(email)) {
                try {
                    paymentDAO = PaymentDAO.getInstance();
                    cardDAO = CardDAO.getInstance();

                    Payment payment = new Payment(((Integer) request
                            .getSession().getAttribute("customerId")),customer.getLogin(),email,cardValue,
                            getDate(), "Prepared");

                    int cardIdToPerson = cardDAO
                            .getCardIdByUserId(customerDAO.showIdByLogin(email));

                    if(cardDAO.transferP2P(card.getCardId(), cardIdToPerson, cardValue)) {
                        System.out.println(card.getCardId() + " "+cardIdToPerson + " "+cardValue);
                        payment.setPaymentStatus("Send");
                        boolean t  = paymentDAO.addPayment(payment);
                        System.out.println(t);
                        card.setBalance(card.getBalance() - cardValue);

                        request.getSession().setAttribute("card", card);

                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cards.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } catch (SQLException e) {
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }

            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("send.jsp");
        requestDispatcher.forward(request, response);
    }
}
