package com.payments.database.DAO;

import com.payments.services.PasswordEncryption;
import com.payments.database.ConnectionPool;
import com.payments.entety.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.List;

import static com.payments.database.SqlQuery.Customer.*;


public class CustomerDAO {

    private static final Logger log = LoggerFactory.getLogger(CustomerDAO.class);
    Connection con ;


    public CustomerDAO() {

    }
    public CustomerDAO(ConnectionPool connectionPool) {
        this.con = connectionPool.getConnection();
    }


    public void setPassword(String login,String password){
        try(PreparedStatement preparedStatement = con
                .prepareStatement(SET_PASSWORD)){
            preparedStatement.setString(1, PasswordEncryption.encryptPasswordSha1(password));
            preparedStatement.setString(2, login);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);

        }
    }

    public List<Customer> getAllCustomerWhereCardStatusPrepare() {
        List<Customer> customerList = new LinkedList<>();

        try (PreparedStatement preparedStatement = con
                .prepareStatement(ALL_CUSTOMERS_WHERE_STATUS_PREPARE)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setUserID(resultSet.getInt(2));
                customer.setStatusOfCard(resultSet.getString(4));
                try (PreparedStatement preparedStatement1 = con
                        .prepareStatement(PAGINATION_ALL_CUSTOMERS_BY_3_TABLES)) {
                    preparedStatement1.setInt(1, customer.getUserID());
                    preparedStatement1.setInt(2, customer.getUserID());
                    preparedStatement1.setInt(3, customer.getUserID());
                    ResultSet resultSet1 = preparedStatement1.executeQuery();
                    while (resultSet1.next()) {
                        customer.setFirstName(resultSet1.getString(2));
                        customer.setSecondName(resultSet1.getString(3));
                        customer.setLogin(resultSet1.getString(4));
                        customer.setPhone(resultSet1.getString(6));
                        customer.setRole(resultSet1.getString(8));
                        customer.setBalance(resultSet1.getInt(11));
                        customerList.add(customer);
                    }


                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return customerList;
    }

    private String getSQL(int sorting) {
        if (Objects.equals(sorting, 1)) return PAGINATION_ALL_CUSTOMERS_BY_ID_EARLIER;
        else if (Objects.equals(sorting, 2)) return PAGINATION_ALL_CUSTOMERS_BY_ID_LATEST;
        else if (Objects.equals(sorting, 3)) return PAGINATION_ALL_CUSTOMERS_BY_NAME_EARLIER;
        else if (Objects.equals(sorting, 4)) return PAGINATION_ALL_CUSTOMERS_BY_NAME_LATEST;
        else if (Objects.equals(sorting, 5)) return PAGINATION_ALL_CUSTOMERS_BY_AMOUNT_EARLIER;
        else return PAGINATION_ALL_CUSTOMERS_BY_AMOUNT_LATEST;
    }

    public List<Customer> getAllCustomers(int sorting, int currentPage, int recordsPerPage) {


        List<Customer> customerList = new LinkedList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement preparedStatement = con
                .prepareStatement(getSQL(sorting))) {

            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, recordsPerPage);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setUserID(resultSet.getInt(1));
                try (PreparedStatement preparedStatement1 = con
                        .prepareStatement(PAGINATION_ALL_CUSTOMERS_BY_3_TABLES)) {
                    preparedStatement1.setInt(1, customer.getUserID());
                    preparedStatement1.setInt(2, customer.getUserID());
                    preparedStatement1.setInt(3, customer.getUserID());
                    preparedStatement1.execute();
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    while (resultSet1.next()) {
                        if (!resultSet1.getString(8).equals("Admin")) {
                            customer.setFirstName(resultSet1.getString(2));
                            customer.setSecondName(resultSet1.getString(3));
                            customer.setLogin(resultSet1.getString(4));
                            customer.setPhone(resultSet1.getString(6));
                            customer.setRole(resultSet1.getString(8));
                            customer.setBalance(resultSet1.getInt(11));
                            customer.setStatusOfCard(resultSet1.getString(12));

                            customerList.add(customer);
                        }
                    }

                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return customerList;
    }


    public Integer getNumberOfRows() {
        int number = 0;
        try (PreparedStatement preparedStatement = con
                .prepareStatement(COUNT_NUM_OF_ROWS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                number = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return number;
    }

    public void addCustomer(Customer customer) {
        try (PreparedStatement preparedStatement = con
                .prepareStatement(INSERT_CUSTOMER)) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getSecondName());
            preparedStatement.setString(3, customer.getLogin());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public Integer getCustomerId(Customer customer) {
        Integer id = null;
        try (PreparedStatement preparedStatement = con.
                             prepareStatement(SELECT_CUSTOMER_LOGIN_Id);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                if (resultSet.getString(1).equals(customer.getLogin())) {
                    id = resultSet.getInt(2);
                    break;
                }
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return id;
    }

    public Customer getCustomerByLogin(String login) {
        Customer customer = null;
        try (PreparedStatement preparedStatement = con
                .prepareStatement(SELECT_CUSTOMERS_BY_LOGIN);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                if (Objects.equals(resultSet.getString("login"), login)) {
                    customer = new Customer(resultSet.getInt(1)
                            , resultSet.getString(2)
                            , resultSet.getString(3)
                            , resultSet.getString(4)
                            , resultSet.getString(5)
                            , resultSet.getString(6));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public boolean searchingByLoginAndPassword(String login, String password) {
        boolean answer = false;
        try (PreparedStatement preparedStatement = con
                .prepareStatement(SELECT_CUSTOMER_LOGIN_AND_PASSWORD);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(login) && resultSet.getString(2).equals(password)) {
                    answer = true;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);

        }
        return answer;
    }

    public boolean searchingByPhone(String phone) {
        boolean count = false;
        try (PreparedStatement preparedStatement = con
                .prepareStatement(SELECT_CUSTOMER_PHONE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(phone)) {
                    count = true;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return count;
    }

    public boolean searchingByLogin(String login) {
        boolean count = false;
        try (PreparedStatement preparedStatement = con
                .prepareStatement(SELECT_CUSTOMER_LOGIN)) {
            preparedStatement.setString(1,login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = true;
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);

        }
        return count;
    }

    public int showIdByLogin(String login) {
        int id = 0;
        try (PreparedStatement preparedStatement = con
                .prepareStatement(SELECT_CUSTOMER_LOGIN_Id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(login)) {
                    id = resultSet.getInt(2);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);

        }
        return id;

    }
    public boolean isExist(int id){
        boolean isExist = false;
        try(PreparedStatement preparedStatement = con.prepareStatement(IS_EXIST_BY_ID)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                isExist = true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return isExist;
    }
    public void deleteCustomer(int id){
        try (PreparedStatement preparedStatement = con
                .prepareStatement(REMOVE_CUSTOMER)){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
