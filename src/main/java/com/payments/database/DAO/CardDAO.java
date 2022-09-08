package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Card;
import com.payments.services.GenerateCardNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

import static com.payments.database.SqlQuery.Card.*;


public class CardDAO {

    private static final Logger log = LoggerFactory.getLogger(CardDAO.class);
    private Connection con;

    public CardDAO() {

    }

    public CardDAO(ConnectionPool connection) {
        this.con = connection.getConnection();
    }

    public boolean isCardExist(String cardNumber){
        boolean isExist = false;
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(IS_CARD_EXIST)){
            preparedStatement.setString(1,cardNumber);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) isExist = true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return  isExist;
    }
    public void setCardStatus (String cardNumber,String status){
        try(PreparedStatement preparedStatement = con
                .prepareStatement(UPDATE_STATUS_BY_CARD_NUM)){
            preparedStatement.setString(1,status);
            preparedStatement.setString(2,cardNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public boolean updateBalanceByCardNumber(String cardNumber, int amount) {
        try (PreparedStatement preparedStatement = con
                .prepareStatement(UPDATE_BALANCE_BY_CARD_NUMBER)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }
    public boolean transferP2P(String numberCardSender, String numberCardRecipient, int value) throws SQLException {
        Connection connection = con;
        boolean isCorrect = false ;
        try{
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_CARD_SET_BALANCE_SENDER)) {
                preparedStatement.setInt(1, value);
                preparedStatement.setString(2, numberCardSender);
                preparedStatement.executeUpdate();
                try (PreparedStatement preparedStatement1 = connection
                        .prepareStatement(UPDATE_CARD_SET_BALANCE_RECIPIENT)) {
                    preparedStatement1.setInt(1, value);
                    preparedStatement1.setString(2, numberCardRecipient);
                    preparedStatement1.executeUpdate();
                } catch (SQLException e) {
                    connection.rollback();
                    log.error(e.getMessage());
                }
                isCorrect = true;
            } catch (SQLException e) {
                isCorrect = false;
            }
            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            log.error(e.getMessage());
        }
        connection.setAutoCommit(true);
        return isCorrect;
    }
    public void creatCard(int id) {
        try (PreparedStatement preparedStatement = con
                .prepareStatement(CREATE_CARD)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, GenerateCardNumber.generateCardNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Card> getCardByCustomerId(int id) {
        Card card ;
        List<Card> cardSet = new LinkedList<>();
        try (PreparedStatement preparedStatement = con
                .prepareStatement(GET_CARD_BY_CUSTOMER_ID)){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                card = new Card(resultSet.getInt(1)
                        , resultSet.getInt(2)
                        , resultSet.getString(3)
                        , resultSet.getInt(4));
                card.setStatus(resultSet.getString(5));
                cardSet.add(card);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return cardSet;
    }
    public String getStatusOfCard (String cardNumber){
        String status = null ;
        try (PreparedStatement preparedStatement = con
                .prepareStatement(GET_CARD_STATUS)) {
            preparedStatement.setString(1, cardNumber);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) status = resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  status;
    }

}