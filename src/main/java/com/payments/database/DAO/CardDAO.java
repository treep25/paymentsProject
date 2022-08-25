package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Card;
import com.payments.entety.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CardDAO {

    private static final Logger log = LoggerFactory.getLogger(CardDAO.class);
    private static CardDAO instance;
    private ConnectionPool connection = ConnectionPool.getInstance();

    private CardDAO() throws SQLException {

    }

    public static synchronized CardDAO getInstance() throws SQLException {
        if (instance != null) return instance;
        instance = new CardDAO();
        return instance;
    }

    public boolean isCardExist(int cardId){
        boolean isExist = false;
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("SELECT * FROM card WHERE card_id = ?")){
            preparedStatement.setInt(1,cardId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                isExist = true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return  isExist;
    }
    public void creatCardForCustomer(int id) {
        try (PreparedStatement preparedStatement = ConnectionPool.getInstance()
                .getConnection().prepareStatement("INSERT INTO card(user_id,balance) VALUES(?,?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, 0);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCardStatus (int cardId,String status){
        try(PreparedStatement preparedStatement = connection
                .getConnection().prepareStatement("UPDATE card SET status = ? WHERE card_id =?")){
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,cardId);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean updateBalanceByCardId(int cardId, int amount) {
        try (PreparedStatement preparedStatement = ConnectionPool.getInstance()
                .getConnection().prepareStatement("UPDATE card SET balance = balance + ? WHERE card_id =?")) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, cardId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }

    public Card getCardById(int id) {
        Card card = new Card();
        try (PreparedStatement preparedStatement = ConnectionPool.getInstance()
                .getConnection().prepareStatement("SELECT * from card");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                if (Objects.equals(resultSet.getInt(2), id)) {
                    card = new Card(resultSet.getInt(1)
                            , resultSet.getInt(2)
                            , resultSet.getInt(3));
                    card.setStatus(resultSet.getString(4));
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return card;
    }

    public int getCardIdByUserId(int userId) {
        int cardId = 0;
        try (PreparedStatement preparedStatement = ConnectionPool.getInstance()
                .getConnection().prepareStatement("SELECT * FROM card WHERE user_id = ?")) {
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cardId = resultSet.getInt(1);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cardId;
    }

    public boolean transferP2P(int cardId1, int cardId2, int value) throws SQLException {

        boolean isCorrect = false;
        Connection con = ConnectionPool.getInstance().getConnection();
        con.setAutoCommit(false);
        try (PreparedStatement preparedStatement = con
                .prepareStatement("UPDATE card SET balance = balance - ? WHERE card_id =?")) {


            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, cardId1);
            preparedStatement.executeUpdate();

            try (PreparedStatement preparedStatement1 = con
                    .prepareStatement("UPDATE card SET balance = balance + ? WHERE card_id =?")) {

                preparedStatement1.setInt(1, value);
                preparedStatement1.setInt(2, cardId2);
                preparedStatement1.executeUpdate();
                con.commit();


            } catch (SQLException e) {
                isCorrect = false;
                con.rollback();
                log.error(e.getMessage());

            }
            isCorrect = true;

        } catch (SQLException e) {
            isCorrect = false;
            con.rollback();
            log.error(e.getMessage());
        }
        con.setAutoCommit(true);
        return isCorrect;
    }
}