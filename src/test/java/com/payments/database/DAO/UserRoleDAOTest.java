package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleDAOTest {
    private final String testURL = "jdbc:mysql://127.0.0.1:3306/paymentsTest";
    private final ConnectionPool connectionPool = new ConnectionPool(testURL);
    private final Connection con = connectionPool.getConnection();
    private final UserRoleDAO userRoleDAO = new UserRoleDAO(connectionPool);
    private int userIdExisting = 1;
    private int userIdNotExisting = 3;


    @BeforeEach
    public void beforeStart() throws SQLException {
        con.setAutoCommit(false);
    }
    @AfterEach
    public void afterStart() throws SQLException {
        con.rollback();
    }


    @Test
    void showUserRoleByIdTest() {
        assertEquals("Customer",userRoleDAO.showUserRoleById(userIdExisting));
        assertNull(userRoleDAO.showUserRoleById(userIdNotExisting));
    }
}