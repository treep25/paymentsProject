package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Customer;
import com.payments.entety.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.payments.database.SqlQuery.UserRole.INSERT_ROLE_USER;

public class UserRoleDAO {

    private static final Logger log = LoggerFactory.getLogger(CustomerDAO.class);
    private Connection con;

    public UserRoleDAO() {

    }

    public UserRoleDAO(ConnectionPool connectionPool) {
        this.con = connectionPool.getConnection();
    }

    public void SetCustomerRoleByID4Customer(int id) {
        try (PreparedStatement preparedStatement = con
                .prepareStatement(INSERT_ROLE_USER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "Customer");
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void SetCustomerRoleByID4ADMIN(int id) {
        try (PreparedStatement preparedStatement = con
                .prepareStatement(INSERT_ROLE_USER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "Admin");
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String showUserRoleById(int id) {
        String userRole = null;
        try (PreparedStatement preparedStatement = con
                .prepareStatement("SELECT * FROM user_role WHERE user_id = ? ")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userRole = resultSet.getString(2);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userRole;
    }

}
