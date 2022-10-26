package com.example.c195_assessment.dao;

import com.example.c195_assessment.JDBC;
import com.example.c195_assessment.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class for User
 *
 * @author Sheldon Handler
 */
public class UserDAO {

    /**
     * ObservableList of each User from the "users" table in database.
     */
    public static ObservableList<User> userObservableList = FXCollections.observableArrayList();

    /**
     * Sets userObservableList from database table "users".
     */
    public static void setUserObservableList() throws SQLException {

        try {

            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT * FROM users;");

            ResultSet resultSet = preparedStatement.executeQuery();

            userObservableList.clear();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("User_ID"), resultSet.getString("User_Name"), resultSet.getString("Password"));
                userObservableList.add(user);
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param userId ID of Contact that is being searched for
     * @return Contact that has the corresponding contactId
     */
    public static User getUserFromId(int userId) {
        User user = null;
        for (int i = 0; i < userObservableList.size(); i++) {
            if (userObservableList.get(i).getUserId() == userId) {
                user = userObservableList.get(i);
            }
        }

        return user;
    }
}