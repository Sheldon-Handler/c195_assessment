package com.example.c195_assessment.dao;

import com.example.c195_assessment.JDBC;
import com.example.c195_assessment.dto.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class for Division
 *
 * @author Sheldon Handler
 */
public class DivisionDAO {

    /**
     * ObservableList of Division
     */
    public static ObservableList<Division> divisionObservableList = FXCollections.observableArrayList();

    /**
     * Sets divisionObservableList to match "first_level_divisions" table in database.
     *
     * @throws SQLException Exception to throw if SQL query fails
     */
    public static void setDivisionObservableList() throws SQLException {

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT * FROM first_level_divisions;");

        ResultSet resultSet = preparedStatement.executeQuery();

        divisionObservableList.clear();

        while (resultSet.next()) {

            Division division = new Division(resultSet.getInt("Division_ID"), resultSet.getString("Division"), resultSet.getInt("Country_ID"));

            divisionObservableList.add(division);
        }

        preparedStatement.close();
    }
}
