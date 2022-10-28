package com.example.c195_assessment.controller;

import com.example.c195_assessment.Customer;
import com.example.c195_assessment.Division;
import com.example.c195_assessment.JavaFXLoader;
import com.example.c195_assessment.dao.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Controller for "Customers.fxml"
 *
 * @author Sheldon Handler
 */
public class CustomersController implements Initializable {

    /**
     * FXML TableView for customersTableView
     */
    @FXML
    protected TableView<Customer> customersTableView;

    /**
     * customerId in customersTableView
     */
    @FXML
    protected TableColumn<Customer, Integer> customerId;

    /**
     * customerName in customersTableView
     */
    @FXML
    protected TableColumn<Customer, String> customerName, /**
     * address in customersTableView
     */
    address, /**
     * postalCode in customersTableView
     */
    postalCode, /**
     * phone in customersTableView
     */
    phone;

    /**
     * division in customersTableView
     */
    @FXML
    protected TableColumn<Division, String> division;

    /**
     * Default constructor.
     */
    public CustomersController() {
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
        this.customersTableView.setItems(CustomerDAO.customerObservableList);

        this.customerId.setCellValueFactory(new PropertyValueFactory<>(customerId.getId()));
        this.customerName.setCellValueFactory(new PropertyValueFactory<>(customerName.getId()));
        this.address.setCellValueFactory(new PropertyValueFactory<>(address.getId()));
        this.postalCode.setCellValueFactory(new PropertyValueFactory<>(postalCode.getId()));
        this.phone.setCellValueFactory(new PropertyValueFactory<>(phone.getId()));
        this.division.setCellValueFactory(new PropertyValueFactory<>(division.getId()));
    }


    /**
     * Opens new "AddCustomer.fxml" window.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onCustomersAddButtonClick(ActionEvent actionEvent) {
        JavaFXLoader javaFXLoader = new JavaFXLoader();
        javaFXLoader.loadFXML("AddCustomer.fxml", ResourceBundle.getBundle("lang", Locale.ROOT).getString("add.customer"), Modality.APPLICATION_MODAL);
    }

    /**
     * Opens new "EditCustomer.fxml" window.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onCustomersEditButtonClick(ActionEvent actionEvent) {
        EditCustomerController.customer = customersTableView.getSelectionModel().getSelectedItem();
        JavaFXLoader javaFXLoader = new JavaFXLoader();
        javaFXLoader.loadFXML("EditCustomer.fxml", ResourceBundle.getBundle("lang", Locale.ROOT).getString("edit.customer"), Modality.APPLICATION_MODAL);
    }

    /**
     * Deletes selected item in customerTableView.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onCustomersDeleteButtonClick(ActionEvent actionEvent) {
        Customer customer = customersTableView.getSelectionModel().getSelectedItem();
        JavaFXLoader javaFXLoader = new JavaFXLoader();

            try {
                CustomerDAO.deleteCustomer(customer);
                DialogController.contentText = ResourceBundle.getBundle("lang").getString("customer.deleted") + " " + customer.getCustomerId();
                javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("deleted"), Modality.APPLICATION_MODAL);
            } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
            }
        }
}
