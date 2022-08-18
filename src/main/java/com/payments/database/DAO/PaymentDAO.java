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
                paymentList.add(new Payment(resultSet.getInt(2)
                        ,resultSet.getTimestamp(3),resultSet.getString(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentList;
    }

    public void addPayment(Payment payment ){
        date = payment.getDate();
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("INSERT INTO payment (user_id,date,payment_status) VALUES (?,?,?)")){

            preparedStatement.setInt(1,payment.getUserId());
            preparedStatement.setTimestamp(2, payment.getDate());
            preparedStatement.setString(3,payment.getPaymentStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }
    public int getPaymentIdByPayment(Payment payment){
        int paymentId = 0;

        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("SELECT * FROM payment");
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                if (payment.getUserId() == resultSet.getInt(2)
                        && payment.getDate() == date
                        && Objects.equals(payment.getPaymentStatus(), resultSet.getString(4))) {
                    paymentId = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return paymentId;
    }


    public void setPaymentStatus(int paymentId){
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("UPDATE payment SET payment_status = ? WHERE payment_id = ?")){

            preparedStatement.setString(1,"Send");
            preparedStatement.setInt(2,paymentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
