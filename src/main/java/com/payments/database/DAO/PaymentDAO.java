package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

import static com.payments.database.SqlQuery.Payment.*;


public class PaymentDAO {

    Connection con;
    private static final Logger log = LoggerFactory.getLogger(PaymentDAO.class);

    private PaymentDAO()  {

    }
    public  PaymentDAO(ConnectionPool connectionPool){
        this.con = connectionPool.getConnection();
    }
    private String getSQL(int sorting){
        if(Objects.equals(sorting,1))return PAGINATION_ALL_PAYMENTS_EARLIER;
        else if (Objects.equals(sorting,2)) return PAGINATION_ALL_PAYMENTS_LATEST ;
        else if (Objects.equals(sorting,3)) return PAGINATION_ALL_PAYMENTS_BY_DATE_EARLIER;
        else return PAGINATION_ALL_PAYMENTS_BY_DATE_LATEST;
    }
    public List<Payment> getAllPaymentsByCustomerId(int id,int sorting,int currentPage, int recordsPerPage){
        List <Payment> paymentList= new LinkedList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        try(PreparedStatement preparedStatement = con
                .prepareStatement(getSQL(sorting))) {
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,start);
            preparedStatement.setInt(3,recordsPerPage);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paymentList.add(new Payment(resultSet.getInt(1)
                        ,resultSet.getInt(2)
                        ,resultSet.getString(3)
                        ,resultSet.getString(4)
                        ,resultSet.getInt(5)
                        ,resultSet.getTimestamp(6).toString()
                        ,resultSet.getString(7)));
            }

        } catch (SQLException e) {

            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return paymentList;
    }
    public Integer getNumberOfRows(int id) {
        int number = 0;
        try(PreparedStatement preparedStatement = con
                .prepareStatement(COUNT_RAWS_PAYMENTS_BY_USER)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                number = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
     return number;
    }

    public void addPayment(Payment payment ){
        try(PreparedStatement preparedStatement = con
                .prepareStatement(INSERT_INTO_PAYMENTS_ALL)){

           preparedStatement.setInt(1,payment.getUserId());
           preparedStatement.setString(2,payment.getCardNumberSender());
           preparedStatement.setString(3,payment.getCardNumberRecipient());
           preparedStatement.setInt(4,payment.getAmount());
           preparedStatement.setTimestamp(5,Timestamp.valueOf(payment.getDate()));
           preparedStatement.setString(6,payment.getPaymentStatus());
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
