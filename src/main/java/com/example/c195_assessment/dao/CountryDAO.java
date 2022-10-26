package com.example.c195_assessment.dao;

import com.example.c195_assessment.Country;
import com.example.c195_assessment.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class for Country
 *
 * @author Sheldon Handler
 */
public class CountryDAO {

    /**
     * ObservableList of Country from database table "countries".
     */
    public static ObservableList<Country> countryObservableList = FXCollections.observableArrayList();

    /**
     * Sets countryObservableList to mach "countries" table in database.
     *
     * @throws SQLException Exception to throw if SQL query fails
     */
    public static void setCountryObservableList() throws SQLException {

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT * FROM countries;");

        ResultSet resultSet = preparedStatement.executeQuery();

        countryObservableList.clear();

        while (resultSet.next()) {

            Country country = new Country(resultSet.getInt("Country_ID"), resultSet.getString("Country"));

            countryObservableList.add(country);
        }

        preparedStatement.close();
    }
}
