package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Card;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardDAOTest {
    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    private final CardDAO cardDAO = new CardDAO(connectionPool);
    private String cardNumberExisting = "9999 9999 9999 9999";
    private String cardNumberNotExisting = "1234 1234 1234 1234";

    @BeforeEach
    public void beforeStart() throws SQLException {
        con.setAutoCommit(false);
    }
    @AfterEach
    public void afterStart() throws SQLException {
        con.rollback();
    }

    @Test
    void isCardExistTest() {
        assertTrue(cardDAO.isCardExist(cardNumberExisting));
        assertFalse(cardDAO.isCardExist(cardNumberNotExisting));
    }

    @Test
    void setCardStatusTest() {
        cardDAO.setCardStatus(cardNumberExisting,"Prepare");
    }

    @Test
    void updateBalanceByCardNumberTest() {
        assertTrue(cardDAO.updateBalanceByCardNumber(cardNumberExisting,1));
    }


    @Test
    void creatCardTest() {
        cardDAO.creatCard(1);
    }


    @Test
    void getStatusOfCardTest() {
        assertEquals("Active",cardDAO.getStatusOfCard(cardNumberExisting));
        assertNull(cardDAO.getStatusOfCard(cardNumberNotExisting));
    }
}