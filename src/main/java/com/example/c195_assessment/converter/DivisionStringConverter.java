package com.example.c195_assessment.converter;

import com.example.c195_assessment.dao.DivisionDAO;
import com.example.c195_assessment.dto.Division;
import javafx.util.StringConverter;

/**
 * StringConverter class for Customer
 *
 * @author Sheldon Handler
 */
public class DivisionStringConverter extends StringConverter<Division> {

    /**
     * Converts the object provided into its string form.
     * Format of the returned string is defined by the specific converter.
     *
     * @param object the object of type {@code Division} to convert
     * @return a string representation of the object passed in
     */
    @Override
    public String toString(Division object) {
        if (object != null) {
            return object.toString();
        } else {
            return "";
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
    public Division fromString(String string) {
        Division division = new Division();
        for (int i = 0; i < DivisionDAO.divisionObservableList.size(); i++) {
            if (DivisionDAO.divisionObservableList.get(i).toString().equals(string)) {
                division = DivisionDAO.divisionObservableList.get(i);
            }
        }
        return division;
    }
}
