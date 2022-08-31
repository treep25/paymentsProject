package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CardDAOTest {
    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://127.0.0.1:3306/paymentsTest");
    private final CardDAO cardDAO = new CardDAO(connectionPool);

    @Test
    void isCardExistTest() {
        int someCardId = 322;
        assertFalse(cardDAO.isCardExist(someCardId));
    }
    @Test
    void isCardNotExistTest() {
        int someCardId = 74;
        assertTrue(cardDAO.isCardExist(someCardId));
    }

    @Test
    void creatCardForCustomer() throws SQLException {
        int testDaoId = 1;
        cardDAO.creatCardForCustomer(testDaoId);
    }

    @Test
    void setCardStatus() {

    }

    @Test
    void updateBalanceByCardId() {
    }

    @Test
    void getCardById() {
    }

    @Test
    void getCardIdByUserId() {
    }

    @Test
    void transferP2P() {
    }
}