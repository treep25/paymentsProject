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

    private CardDAO() {

    }

    public static synchronized CardDAO getInstance() {
        if (instance != null) return instance;
        instance = new CardDAO();
        return instance;
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

    // sorting by amount of balance
    public void SortingByBalance(int userId) {
        try (PreparedStatement preparedStatement = ConnectionPool.getInstance()
                .getConnection().prepareStatement("SELECT * FROM card ORDER BY balance DESC ");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public Integer findCardByUserId(int id) {
        return null;
    }

    public Integer showUserBalanceById(int id) {
        int balance = 0;
        try (PreparedStatement preparedStatement = ConnectionPool.getInstance()
                .getConnection().prepareStatement("SELECT * from card  ");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                if (Objects.equals(resultSet.getInt(2), id)) {
                    balance = resultSet.getInt(3);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return balance;
    }

    public void updateBalanceByCardId(int carId, int balance) {
        try (PreparedStatement preparedStatement = ConnectionPool.getInstance()
                .getConnection().prepareStatement("UPDATE card SET balance = balance + ? WHERE card_id =?")) {
            preparedStatement.setInt(1, balance);
            preparedStatement.setInt(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

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

    public void transferP2P(int cardId1, int cardId2, int value) throws SQLException {

        try (PreparedStatement preparedStatement = ConnectionPool.getInstance().getConnection()
                .prepareStatement("UPDATE card SET balance = balance - ? WHERE card_id =?")) {

            ConnectionPool.getInstance().getConnection().setAutoCommit(false);

            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, cardId1);
            preparedStatement.executeUpdate();

            try (PreparedStatement preparedStatement1 = ConnectionPool.getInstance().getConnection()
                    .prepareStatement("UPDATE card SET balance = balance + ? WHERE card_id =?")) {

                preparedStatement1.setInt(1, value);
                preparedStatement1.setInt(2, cardId2);
                preparedStatement1.executeUpdate();

            } catch (SQLException e) {
                log.error(e.getMessage());
                throw new RuntimeException();
            }
            ConnectionPool.getInstance().getConnection().commit();

        } catch (SQLException e) {
            try {
                ConnectionPool.getInstance().getConnection().rollback();

            } catch (SQLException e1) {
                log.error(e1.getMessage());
                throw new RuntimeException();
            }
        }

    }
}