package com.example.c195_assessment.converter;

import com.example.c195_assessment.Country;
import com.example.c195_assessment.dao.CountryDAO;
import javafx.util.StringConverter;

/**
 * StringConverter subclass for Country
 *
 * @author Sheldon Handler
 */
public class CountryStringConverter extends StringConverter<Country> {

    /**
     * Converts the object provided into its string form.
     * Format of the returned string is defined by the specific converter.
     *
     * @param object the object of type {@code Country} to convert
     * @return a string representation of the object passed in
     */
    @Override
    public String toString(Country object) {
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
    public Country fromString(String string) {
        Country country = new Country();
        for (int i = 0; i < CountryDAO.countryObservableList.size(); i++) {
            if (CountryDAO.countryObservableList.get(i).getCountry().equals(string)) {
                country = CountryDAO.countryObservableList.get(i);
            }
        }
        return country;
    }
}
