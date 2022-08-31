package com.payments.web.servlets;

import com.payments.database.ConnectionPool;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SendTransfer", value = "/SendTransfer")
public class SendTransfer extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SendTransfer.class);
    private CustomerDAO customerDAO ;
    private PaymentDAO paymentDAO ;
    private CardDAO cardDAO;

    public String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().removeAttribute("warning");
        String recipientEmail = request.getParameter("recipient");
        int amount = Integer.parseInt(request.getParameter("amount"));
        Card card = (Card) request.getSession().getAttribute("card");
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        ConnectionPool connectionPool = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        CustomerDAO customerDAO = new CustomerDAO(connectionPool);
        CardDAO cardDAO= new CardDAO(connectionPool);
        PaymentDAO paymentDAO = new PaymentDAO(connectionPool);

        if(!recipientEmail.isEmpty() && customerDAO.searchingByLogin(recipientEmail) && amount>0
                && card.getBalance()>amount){

            int recipientID = customerDAO.showIdByLogin(recipientEmail);
            int recipientCardId = cardDAO.getCardIdByUserId(recipientID);

            Payment payment = new Payment(customer.getUserID(),customer.getLogin(),
                    recipientEmail,amount,getDate(),"Prepared");

            try {
                if(!cardDAO.transferP2P(card.getCardId(),recipientCardId , amount)){
                    paymentDAO.addPayment(payment);
                }else{
                    payment.setPaymentStatus("Send");
                    paymentDAO.addPayment(payment);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            card.setBalance(card.getBalance()-amount);
            request.getSession().setAttribute("card",card);

            response.sendRedirect("http://localhost:8080/cards.jsp");

        }
        else {
            request.getSession().setAttribute("warning","email.has`t.existed.or.You.haven`t.enough.money");
            request.getRequestDispatcher("send.jsp").forward(request,response);
        }


    }

}
