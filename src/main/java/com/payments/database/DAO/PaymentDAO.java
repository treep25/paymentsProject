package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class PaymentDAO {

    ConnectionPool connection = ConnectionPool.getInstance();
    private static final Logger log = LoggerFactory.getLogger(PaymentDAO.class);
    private static PaymentDAO instance;

    private  Timestamp date;


    private PaymentDAO() throws SQLException {

    }

    public static synchronized PaymentDAO getInstance() throws SQLException {
        if (instance != null) return instance;
        instance = new PaymentDAO();
        return instance;
    }

    public List<Payment> getAllPaymentsByCustomerId(int id){
        List <Payment> paymentList= new LinkedList<>();
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("SELECT * FROM payment WHERE user_id = ?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paymentList.add(new Payment(resultSet.getInt(1)
                        ,resultSet.getInt(2)
                        ,resultSet.getString(3)
                        ,resultSet.getString(4)
                        ,resultSet.getInt(5)
                        ,resultSet.getTimestamp(6)
                        ,resultSet.getString(7)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentList;
    }

    public boolean addPayment(Payment payment ){
        boolean t = false;
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("INSERT INTO payment VALUES (DEFAULT,?,?,?,?,?,?)")){

           preparedStatement.setInt(1,payment.getUserId());
           preparedStatement.setString(2,payment.getEmailSender());
           preparedStatement.setString(3,payment.getEmailRecipient());
           preparedStatement.setInt(4,payment.getAmount());
           preparedStatement.setTimestamp(5,payment.getDate());
           preparedStatement.setString(6,payment.getPaymentStatus());
           preparedStatement.execute();
           t = true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return t;

    }
    // smth wrong
    public int getPaymentIdByPayment(Payment payment){
        int paymentId = 0;
        date = payment.getDate();
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("SELECT * FROM payment");
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                if (Objects.equals(payment.getUserId(),resultSet.getInt(2))
                        && Objects.equals(payment.getDate() ,date)
                        && Objects.equals(payment.getPaymentStatus(), resultSet.getString(7))
                        && Objects.equals(payment.getAmount(),resultSet.getInt(5))
                        && Objects.equals(payment.getEmailSender(),resultSet.getString(3))
                        &&Objects.equals(payment.getEmailRecipient(),resultSet.getString(4))){

                    paymentId = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return paymentId;
    }


}
