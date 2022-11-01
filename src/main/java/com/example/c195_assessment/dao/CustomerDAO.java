package com.example.c195_assessment.dao;

import com.example.c195_assessment.JDBC;
import com.example.c195_assessment.dto.Customer;
import com.example.c195_assessment.dto.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class for Customer
 *
 * @author Sheldon Handler
 */
public class CustomerDAO {

    /**
     * ObservableList of Customer from database with Integer matching "Customer_ID" from "customers" table.
     */
    public static ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    /**
     * Setter for customerHashMap
     * Sets customerHashMap based on ResultSet from "customers" table in database.
     *
     * @throws SQLException SQLException to throw if SQL query fails
     */
    public static void setCustomerObservableList() throws SQLException {

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT * FROM customers;");

        ResultSet resultSet = preparedStatement.executeQuery();

        customerObservableList.clear();

        while (resultSet.next()) {

            // matching foreign key "Division_ID" with division from "first_level_divisions" table
            Division division = new Division();
            for (int i = 0; i < DivisionDAO.divisionObservableList.size(); i++) {
                if (DivisionDAO.divisionObservableList.get(i).getDivisionId() == resultSet.getInt("Division_ID")) {
                    division = DivisionDAO.divisionObservableList.get(i);
                }
            }

            Customer customer = new Customer(resultSet.getInt("Customer_ID"), resultSet.getString("Customer_Name"), resultSet.getString("Address"), resultSet.getString("Postal_Code"), resultSet.getString("Phone"), division);

            customerObservableList.add(customer);
        }

        preparedStatement.close();
    }

    /**
     * adds a new Customer to the "customers" SQL table
     *
     * @param customer Customer to add
     * @throws SQLException Exception thrown if SQL query fails
     */
    public static void addCustomer(Customer customer) throws SQLException {

        // preparing SQL statement
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?);");
        preparedStatement.setString(1, customer.getCustomerName());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getPostalCode());
        preparedStatement.setString(4, customer.getPhone());
        preparedStatement.setInt(5, customer.getDivision().getDivisionId());

        // executing prepared statement
        preparedStatement.executeUpdate();
        preparedStatement.close();

        // updating customerObservableList
        setCustomerObservableList();
    }

    /**
     * replaces the fields the record for corresponding customerId with new data.
     *
     * @param customer Customer with modified data but same customerId
     * @throws SQLException exception thrown if SQL statement fails
     */
    public static void modifyCustomer(Customer customer) throws SQLException {


        // preparing SQL statement
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?;");
        preparedStatement.setString(1, customer.getCustomerName());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getPostalCode());
        preparedStatement.setString(4, customer.getPhone());
        preparedStatement.setInt(5, customer.getDivision().getDivisionId());
        preparedStatement.setInt(6, customer.getCustomerId());

        // executing prepared statement
        preparedStatement.executeUpdate();
        preparedStatement.close();

        // updating customerObservableList
        setCustomerObservableList();
    }

    /**
     * deletes the customer with matching Customer_ID from SQL table "customers"
     *
     * @param customer Customer to delete from database table "customers"
     * @throws SQLException exception thrown if SQL statement fails
     */
    public static void deleteCustomer(Customer customer) throws SQLException {

        // preparing SQL statement
        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("DELETE FROM client_schedule.appointments WHERE Customer_ID = ?");
        PreparedStatement preparedStatement1 = JDBC.getConnection().prepareStatement("DELETE FROM client_schedule.customers WHERE Customer_ID = ?");

        preparedStatement.setInt(1, customer.getCustomerId());
        preparedStatement1.setInt(1, customer.getCustomerId());

        // executing prepared statement
        preparedStatement.executeUpdate();
        preparedStatement1.executeUpdate();
        preparedStatement.close();
        preparedStatement1.close();

        // updating customerObservableList
        setCustomerObservableList();

        // updating appointmentObservableList
        AppointmentDAO.setAppointmentObservableList();
    }
}