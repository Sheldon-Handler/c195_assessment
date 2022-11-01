package com.example.c195_assessment.controller;

import com.example.c195_assessment.Country;
import com.example.c195_assessment.Customer;
import com.example.c195_assessment.Division;
import com.example.c195_assessment.converter.CountryStringConverter;
import com.example.c195_assessment.converter.DivisionStringConverter;
import com.example.c195_assessment.dao.CountryDAO;
import com.example.c195_assessment.dao.CustomerDAO;
import com.example.c195_assessment.dao.DivisionDAO;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Controller for CustomersInDivision.fxml
 *
 * @author Sheldon Handler
 */
public class CustomersInDivisionController implements Initializable {

    /**
     * ComboBox for choosing the Country
     */
    @FXML
    protected ComboBox<Country> countryComboBox;

    /**
     * ComboBox for choosing the Division
     */
    @FXML
    protected ComboBox<Division> divisionComboBox;

    /**
     * TableView of Customer to view FilteredList
     */
    @FXML
    protected TableView<Customer> customerTableView;

    /**
     * ID of Customer in customerTableView
     */
    @FXML
    protected TableColumn<Customer, Integer> customerId;

    /**
     * name of Customer
     */
    @FXML
    protected TableColumn<Customer, String> customerName, /**
     * address of Customer
     */
    address, /**
     * postalCode of Customer
     */
    postalCode, /**
     * phone of Customer
     */
    phone;

    /**
     * Division of Customer
     */
    @FXML
    protected TableColumn<Division, String> division;

    /**
     * FilteredList of Customer for data displayed in customerTableView
     */
    private FilteredList<Customer> customerFilteredList = new FilteredList<>(CustomerDAO.customerObservableList);

    /**
     * FilteredList of Division for data in divisionComboBox
     */
    private FilteredList<Division> divisionFilteredList = new FilteredList<>(DivisionDAO.divisionObservableList);

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        customerTableView.setItems(customerFilteredList);

        this.customerId.setCellValueFactory(new PropertyValueFactory<>(this.customerId.getId()));
        this.customerName.setCellValueFactory(new PropertyValueFactory<>(this.customerName.getId()));
        this.address.setCellValueFactory(new PropertyValueFactory<>(this.address.getId()));
        this.postalCode.setCellValueFactory(new PropertyValueFactory<>(this.postalCode.getId()));
        this.phone.setCellValueFactory(new PropertyValueFactory<>(this.phone.getId()));
        this.division.setCellValueFactory(new PropertyValueFactory<>(this.division.getId()));

        countryComboBox.setConverter(new CountryStringConverter());
        countryComboBox.setItems(CountryDAO.countryObservableList);

        divisionComboBox.setConverter(new DivisionStringConverter());
        divisionComboBox.setItems(divisionFilteredList);
    }

    /**
     * Runs in FXML onAction() function for countryComboBox
     * Filters customerTableView by the Country that is selected countryComboBox
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onCountryAction(ActionEvent actionEvent) {

        if (countryComboBox.getSelectionModel().isEmpty()) {

            divisionComboBox.setDisable(true);
            divisionComboBox.getSelectionModel().clearSelection();
            customerFilteredList.setPredicate(customer -> true);

        } else {

            customerFilteredList.setPredicate(customer -> {
                if (customer.getDivision().getCountryId() == countryComboBox.getSelectionModel().getSelectedItem().getCountryId()) {
                    return true;
                } else {
                    return false;
                }
            });

            divisionComboBox.getSelectionModel().clearSelection();

            // setting items in divisionFilteredList
            divisionFilteredList.setPredicate(division1 -> {
                if (division1.getCountryId() == countryComboBox.getSelectionModel().getSelectedItem().getCountryId()) {
                    return true;
                } else {
                    return false;
                }
            });

            divisionComboBox.setDisable(false);
        }
    }

    /**
     * Runs in FXML onAction() function for countryComboBox
     * Filters customerTableView by Division selected in divisionComboBox
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onDivisionAction(ActionEvent actionEvent) {

        if (divisionComboBox.getSelectionModel().isEmpty()) {

            customerFilteredList.setPredicate(customer -> {
                if (customer.getDivision().getCountryId() == countryComboBox.getSelectionModel().getSelectedItem().getCountryId()) {
                    return true;
                } else {
                    return false;
                }
            });

        } else {

            customerFilteredList.setPredicate(customer -> {
                if (customer.getDivision().equals(divisionComboBox.getSelectionModel().getSelectedItem())) {
                    return true;
                } else {
                    return false;
                }
            });
        }
    }
}
