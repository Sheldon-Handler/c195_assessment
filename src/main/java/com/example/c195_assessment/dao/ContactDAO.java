package com.example.c195_assessment.dao;

import com.example.c195_assessment.JDBC;
import com.example.c195_assessment.dto.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class for Contact
 *
 * @author Sheldon Handler
 */
public class ContactDAO {

    /**
     * ObservableList of Contact
     */
    public static ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();

    /**
     * Sets contactObservableList to match "contacts" table in database.
     *
     * @throws SQLException SQLException to throw if SQL query fails
     */
    public static void setContactObservableList() throws SQLException {

        PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT * FROM contacts;");

        ResultSet resultSet = preparedStatement.executeQuery();

        contactObservableList.clear();

        while (resultSet.next()) {
            Contact contact = new Contact(resultSet.getInt("Contact_ID"), resultSet.getString("Contact_Name"), resultSet.getString("Email"));
            contactObservableList.add(contact);
        }

        preparedStatement.close();
    }

    /**
     * @param contactId ID of Contact that is being searched for
     * @return Contact that has the corresponding contactId
     */
    public static Contact getContactFromId(int contactId) {
        Contact contact = null;
        for (int i = 0; i < contactObservableList.size(); i++) {
            if (contactObservableList.get(i).getContactId() == contactId) {
                contact = contactObservableList.get(i);
            }
        }

        return contact;
    }
}
