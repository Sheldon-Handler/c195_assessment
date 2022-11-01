package com.example.c195_assessment.converter;

import com.example.c195_assessment.dao.CustomerDAO;
import com.example.c195_assessment.dto.Customer;
import javafx.util.StringConverter;

/**
 * StringConverter class for Customer
 *
 * @author Sheldon Handler
 */
public class CustomerStringConverter extends StringConverter<Customer> {

    /**
     * Converts the object provided into its string form.
     * Format of the returned string is defined by the specific converter.
     *
     * @param object the object of type {@code Customer} to convert
     * @return a string representation of the object passed in
     */
    @Override
    public String toString(Customer object) {
        if (object == null || object.getCustomerName() == null) {
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
    public Customer fromString(String string) {
        Customer customer = new Customer();
        for (int i = 0; i < CustomerDAO.customerObservableList.size(); i++) {
            if (CustomerDAO.customerObservableList.get(i).toString().equals(string)) {
                customer = CustomerDAO.customerObservableList.get(i);
            }
        }
        return customer;
    }
}
