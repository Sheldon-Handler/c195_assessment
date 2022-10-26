package com.example.c195_assessment.converter;

import com.example.c195_assessment.User;
import com.example.c195_assessment.dao.UserDAO;
import javafx.util.StringConverter;

/**
 * StringConverter subclass for User
 *
 * @author Sheldon Handler
 */
public class UserStringConverter extends StringConverter<User> {

    /**
     * Converts the object provided into its string form.
     * Format of the returned string is defined by the specific converter.
     *
     * @param object the object of type {@code User} to convert
     * @return a string representation of the object passed in
     */
    @Override
    public String toString(User object) {
        if (object == null || object.getUserName() == null) {
            return "";
        } else {
            return object.toString();
        }
    }

    /**
     * Converts the string provided into an object defined by the specific converter.
     * Format of the string and type of the resulting object is defined by the specific converter.
     *
     * @param string the {@code String} to convert
     * @return an object representation of the string passed in.
     */
    @Override
    public User fromString(String string) {
        User user = new User();
        for (int i = 0; i < UserDAO.userObservableList.size(); i++) {
            if (UserDAO.userObservableList.get(i).toString().equals(string)) {
                user = UserDAO.userObservableList.get(i);
            }
        }
        return user;
    }
}
