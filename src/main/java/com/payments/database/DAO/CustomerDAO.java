package com.payments.database.DAO;

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
    private static CustomerDAO instance;


    private CustomerDAO (){

    }

    public static synchronized CustomerDAO getInstance(){
        if(instance!= null) return instance;
        instance = new CustomerDAO();
        return instance;
    }

    public List <Customer> getAllCustomers(){
        List <Customer> customerList = new LinkedList<>();
        try(PreparedStatement preparedStatement = ConnectionPool.getInstance().getConnection().prepareStatement(SELECT_ALL_CUSTOMERS);
            ResultSet resultSet = preparedStatement.executeQuery();
                ){
            while(resultSet.next()){
                customerList.add(new Customer(resultSet.getInt("user_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("second_name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("phone")));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return customerList;
    }
    public void addCustomer(Customer customer) {
        try(PreparedStatement preparedStatement = ConnectionPool.getInstance()
                .getConnection().prepareStatement(INSERT_CUSTOMER)){

            preparedStatement.setString(1,customer.getFirstName());
            preparedStatement.setString(2,customer.getSecondName());
            preparedStatement.setString(3,customer.getLogin());
            preparedStatement.setString(4,customer.getPassword());
            preparedStatement.setString(5,customer.getPhone());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public Integer getCustomerId (Customer customer){
        Integer id = null ;
        try (PreparedStatement preparedStatement =
                     ConnectionPool.getInstance().getConnection().
                             prepareStatement(SELECT_CUSTOMER_LOGIN_Id);
             ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                if(resultSet.getString(1).equals(customer.getLogin()))
                {
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

    public Customer getCustomerByLogin(String login){
        Customer customer = null;
        try(PreparedStatement preparedStatement =ConnectionPool.getInstance()
                .getConnection().prepareStatement(SELECT_CUSTOMERS_BY_LOGIN);
        ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                if (Objects.equals(resultSet.getString("login"), login)){
                    customer = new Customer(resultSet.getInt(1)
                            ,resultSet.getString(2)
                            ,resultSet.getString(3)
                            ,resultSet.getString(4)
                            ,resultSet.getString(5)
                            ,resultSet.getString(6));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public boolean searchingByLoginAndPassword(String login , String password){
        boolean answer = false;
        try(PreparedStatement preparedStatement =
                    ConnectionPool.getInstance().getConnection().prepareStatement(SELECT_CUSTOMER_LOGIN_AND_PASSWORD);
                    ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                if(resultSet.getString(1).equals(login) && resultSet.getString(2).equals(password)){
                    answer = true;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);

        }
        return answer;
    }
    public boolean searchingByLogin(String login){
        boolean count = false;
        try(PreparedStatement preparedStatement =
                    ConnectionPool.getInstance().getConnection().prepareStatement(SELECT_CUSTOMER_LOGIN);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                if(resultSet.getString(1).equals(login)){
                    count = true;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);

        }
        return count;
    }
    public int showIdByLogin (String login){
        int id = 0;
        try(PreparedStatement preparedStatement =
                    ConnectionPool.getInstance().getConnection().prepareStatement(SELECT_CUSTOMER_LOGIN_Id);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                if(resultSet.getString(1).equals(login)){
                    id =  resultSet.getInt(2);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);

        }
        return id;

    }



}
