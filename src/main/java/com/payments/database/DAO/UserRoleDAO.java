package com.payments.database.DAO;

import com.payments.database.ConnectionPool;
import com.payments.entety.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.payments.database.SqlQuery.UserRole.INSERT_ROLE_USER;

public class UserRoleDAO {

    private static final Logger log = LoggerFactory.getLogger(CustomerDAO.class);
    private static UserRoleDAO instance;

    private UserRoleDAO (){

    }

    public static synchronized UserRoleDAO getInstance(){
        if(instance!= null) return instance;
        instance = new UserRoleDAO();
        return instance;
    }

    public void SetCustomerRoleByID4Customer (int id){
        try(PreparedStatement preparedStatement =
                    ConnectionPool.getInstance().getConnection().
                            prepareStatement(INSERT_ROLE_USER)){
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,"Customer");
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void SetCustomerRoleByID4ADMIN (int id){
        try(PreparedStatement preparedStatement =
                    ConnectionPool.getInstance().getConnection().
                            prepareStatement(INSERT_ROLE_USER)){
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,"Admin");
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
