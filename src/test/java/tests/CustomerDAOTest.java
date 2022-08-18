package tests;


import com.payments.database.ConnectionPool;
import com.payments.database.DAO.CustomerDAO;
import com.payments.database.SqlQuery;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CustomerDAOTest {

    CustomerDAO cs = CustomerDAO.getInstance();

    @Test
    public void GetAllCustomerTest(){

    }

}
