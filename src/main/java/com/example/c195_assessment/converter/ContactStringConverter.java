package com.example.c195_assessment.converter;

import com.example.c195_assessment.dao.ContactDAO;
import com.example.c195_assessment.dto.Contact;
import javafx.util.StringConverter;

/**
 * StringConverter class for Contact
 *
 * @author Sheldon Handler
 */
public class ContactStringConverter extends StringConverter<Contact> {

    /**
     * Converts the object provided into its string form.
     * Format of the returned string is defined by the specific converter.
     *
     * @param object the object of type {@code Contact} to convert
     * @return a string representation of the object passed in
     */
    @Override
    public String toString(Contact object) {
        if (object == null || object.getContactName() == null || object.getEmail() == null) {
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
    public Contact fromString(String string) {

        Contact contact = null;
        for (int i = 0; i < ContactDAO.contactObservableList.size(); i++) {
            if (ContactDAO.contactObservableList.get(i).toString().equals(string)) {
                contact = ContactDAO.contactObservableList.get(i);
            }
        }
        return contact;
    }
}
