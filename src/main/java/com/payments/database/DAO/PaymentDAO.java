package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.payments.database.SqlQuery.Payment.*;


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
    private String getSQL(int sorting){
        if(Objects.equals(sorting,1))return PAGINATION_ALL_PAYMENTS_EARLIER;
        else if (Objects.equals(sorting,-1)) return PAGINATION_ALL_PAYMENTS_LATEST ;
        else if (Objects.equals(sorting,2)) return PAGINATION_ALL_PAYMENTS_BY_DATE_EARLIER;
        else return PAGINATION_ALL_PAYMENTS_BY_DATE_LATEST;
    }


    public List<Payment> getAllPaymentsByCustomerId(int id,int sorting,int currentPage, int recordsPerPage){

        List <Payment> paymentList= new LinkedList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;

        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement(getSQL(sorting))) {
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
                        ,resultSet.getTimestamp(6).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"))
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
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement(COUNT_RAWS_PAYMENTS_BY_USER)) {
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
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("INSERT INTO payment VALUES (DEFAULT,?,?,?,?,?,?)")){

           preparedStatement.setInt(1,payment.getUserId());
           preparedStatement.setString(2,payment.getEmailSender());
           preparedStatement.setString(3,payment.getEmailRecipient());
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
