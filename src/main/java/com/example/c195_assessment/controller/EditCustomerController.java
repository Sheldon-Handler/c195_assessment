package com.example.c195_assessment.controller;

import com.example.c195_assessment.JavaFXLoader;
import com.example.c195_assessment.converter.CountryStringConverter;
import com.example.c195_assessment.converter.DivisionStringConverter;
import com.example.c195_assessment.dao.CountryDAO;
import com.example.c195_assessment.dao.CustomerDAO;
import com.example.c195_assessment.dao.DivisionDAO;
import com.example.c195_assessment.dto.Country;
import com.example.c195_assessment.dto.Customer;
import com.example.c195_assessment.dto.Division;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Controller for "EditCustomer.fxml"
 *
 * @author Sheldon Handler
 */
public class EditCustomerController implements Initializable {

    /**
     * Customer that is passed to this item.
     */
    public static Customer customer;
    /**
     * TextField for customerId
     */
    @FXML
    protected TextField customerId, /**
     * TextField for customerName
     */
    customerName, /**
     * TextField for address
     */
    address, /**
     * TextField for city
     */
    city, /**
     * TextField for postal code
     **/
    postal, /**
     * TextField for phone number
     */
    phone;
    /**
     * ChoiceBox for Country
     */
    @FXML
    protected ChoiceBox<Country> country;
    /**
     * ChoiceBox for Division
     */
    @FXML
    protected ChoiceBox<Division> division;
    /**
     * Button for Save
     */
    @FXML
    protected Button saveButton, /**
     * Button for Cancel
     */
    cancelButton;

    /**
     * Default constructor.
     */
    public EditCustomerController() {
    }

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

        customerId.setText(String.valueOf(customer.getCustomerId()));
        customerName.setText(customer.getCustomerName());

        // separating address and city if city is found inside address
        try {
            address.setText(customer.getAddress().substring(0, customer.getAddress().lastIndexOf(", ")));
            city.setText(customer.getAddress().substring(customer.getAddress().lastIndexOf(", ") + 2));
        } catch (RuntimeException runtimeException) {
            address.setText(customer.getAddress());
        }

        postal.setText(customer.getPostalCode());
        phone.setText(customer.getPhone());

        country.setConverter(new CountryStringConverter());
        country.setItems(CountryDAO.countryObservableList);

        division.setConverter(new DivisionStringConverter());
        division.setItems(DivisionDAO.divisionObservableList);

        // This function adds a Listener for Country to run onCountrySelected() whenever an item in Country ComboBox is selected.
        country.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> onCountrySelected());

        // selecting the Country for customer
        for (int i = 0; i < country.getItems().size(); i++) {
            if (country.getItems().get(i).getCountryId() == customer.getDivision().getCountryId()) {
                country.getSelectionModel().select(i);
                break;
            }
        }

        // selecting the Division from customer
        division.getSelectionModel().select(customer.getDivision());
    }

    /**
     * Enables selection of Division and sets the items to FilteredList of each division in selected Country
     * Lambda expression sets ComboBox "division" to display each Division in Country as a selectable option.
     */
    @FXML
    public void onCountrySelected() {

        // enable selection of division ChoiceBox
        division.setDisable(false);

        // creating FilteredList for Division
        FilteredList<Division> divisionFilteredList = new FilteredList<>(DivisionDAO.divisionObservableList);

        // setting divisionFilteredList to only display list of Division contained in currently selected Country
        divisionFilteredList.setPredicate(division1 -> {
            if (division1.getCountryId() == country.getValue().getCountryId()) {
                return true;
            } else {
                return false;
            }
        });

        // setting items in division to mach divisionFilteredList
        division.setItems(divisionFilteredList);
    }

    /**
     * Runs when saveButton is clicked.
     * Checks if data entered is valid.
     * Saves data and closes window data is valid.
     * Creates "Dialog.fxml" popup window with correct error message.
     *
     * @param actionEvent ActionEvent used for closing window
     * @throws SQLException SQLException to throw if SQL query fails
     */
    @FXML
    public void onSaveButtonAction(ActionEvent actionEvent) throws SQLException {
        JavaFXLoader javaFXLoader = new JavaFXLoader();

        if (customerName.getCharacters().isEmpty() || address.getCharacters().isEmpty() || city.getCharacters().isEmpty() || postal.getCharacters().isEmpty() || phone.getCharacters().isEmpty() || country.getSelectionModel().isEmpty() || division.getSelectionModel().isEmpty()) {
            DialogController.contentText = ResourceBundle.getBundle("lang").getString("customer.details.incomplete");
            javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("error"), Modality.APPLICATION_MODAL);
        } else {
            Customer customer = new Customer(Integer.parseInt(customerId.getText()), customerName.getText(), address.getText() + ", " + city.getText(), postal.getText(), phone.getText(), division.getSelectionModel().getSelectedItem(), country.getSelectionModel().getSelectedItem());
            CustomerDAO.modifyCustomer(customer);
            javaFXLoader.closeFXML(actionEvent);
        }
    }

    /**
     * Closes the current window without saving the data entered into any of the fields.
     *
     * @param actionEvent ActionEvent to pass for closing FXML window
     */
    @FXML
    public void onCancelButtonAction(ActionEvent actionEvent) {
        JavaFXLoader javaFXLoader = new JavaFXLoader();
        javaFXLoader.closeFXML(actionEvent);
    }
}
