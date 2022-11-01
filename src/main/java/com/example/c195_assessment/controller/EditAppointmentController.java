package com.example.c195_assessment.controller;

import com.example.c195_assessment.AppointmentValidator;
import com.example.c195_assessment.JavaFXLoader;
import com.example.c195_assessment.converter.ContactStringConverter;
import com.example.c195_assessment.converter.CustomerStringConverter;
import com.example.c195_assessment.converter.UserStringConverter;
import com.example.c195_assessment.dao.AppointmentDAO;
import com.example.c195_assessment.dao.ContactDAO;
import com.example.c195_assessment.dao.CustomerDAO;
import com.example.c195_assessment.dao.UserDAO;
import com.example.c195_assessment.dto.Appointment;
import com.example.c195_assessment.dto.Contact;
import com.example.c195_assessment.dto.Customer;
import com.example.c195_assessment.dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Controller for "EditAppointment.fxml"
 *
 * @author Sheldon Handler
 */
public class EditAppointmentController implements Initializable {

    /**
     * Appointment that is passed to this item.
     */
    public static Appointment appointment;
    /**
     * TextField for ID
     */
    @FXML
    protected TextField id, /**
     * TextField for title
     */
    title, /**
     * TextField for description
     */
    description, /**
     * TextField for type
     */
    type, /**
     * TextField for location
     */
    location;
    /**
     * DatePicker for startDate
     */
    @FXML
    protected DatePicker startDate, /**
     * DatePickwe for endDate
     */
    endDate;
    /**
     * TextField for startTime
     */
    @FXML
    protected TextField startTime, /**
     * TextField for endTime
     */
    endTime;
    /**
     * ComboBox for Customer
     */
    @FXML
    protected ComboBox<Customer> customer;
    /**
     * ComboBox for User
     */
    @FXML
    protected ComboBox<User> user;
    /**
     * ComboBox for Contact
     */
    @FXML
    protected ComboBox<Contact> contact;
    /**
     * Save Button for saving information in form and closing form
     */
    @FXML
    protected Button saveButton, /**
     * Cancel Button for closing form without saving any changes
     */
    cancelButton;

    /**
     * Default constructor.
     */
    public EditAppointmentController() {
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

        customer.setConverter(new CustomerStringConverter());
        customer.setItems(CustomerDAO.customerObservableList);

        Customer customer1 = null;
        for (int i = 0; i < CustomerDAO.customerObservableList.size(); i++) {
            if (CustomerDAO.customerObservableList.get(i).getCustomerId() == appointment.getCustomerId()) {
                customer1 = CustomerDAO.customerObservableList.get(i);
            }
        }
        customer.setValue(customer1);


        user.setConverter(new UserStringConverter());
        user.setItems(UserDAO.userObservableList);

        User user1 = null;
        for (int i = 0; i < UserDAO.userObservableList.size(); i++) {
            if (UserDAO.userObservableList.get(i).getUserId() == appointment.getUserId()) {
                user1 = UserDAO.userObservableList.get(i);
            }
        }
        user.setValue(user1);

        contact.setConverter(new ContactStringConverter());
        contact.setItems(ContactDAO.contactObservableList);
        contact.setValue(appointment.getContact());

        this.id.setText(String.valueOf(appointment.getAppointmentId()));
        this.title.setText(appointment.getTitle());
        this.description.setText(appointment.getDescription());
        this.type.setText(appointment.getType());
        this.location.setText(appointment.getLocation());
        this.startDate.setValue(appointment.getStart().toLocalDate());
        this.startTime.setText(appointment.getStart().toLocalTime().toString());
        this.endDate.setValue(appointment.getEnd().toLocalDate());
        this.endTime.setText(appointment.getEnd().toLocalTime().toString());
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

        if (startTime.getCharacters().length() == 4 && startTime.getCharacters().charAt(1) == ':') {
            startTime.setText("0" + startTime.getText());
        }

        if (endTime.getCharacters().length() == 4 && endTime.getCharacters().charAt(1) == ':') {
            endTime.setText("0" + endTime.getText());
        }

        LocalTime startLocalTime = null, endLocalTime = null;

        try {
            startLocalTime = LocalTime.parse(startTime.getText(), DateTimeFormatter.ISO_LOCAL_TIME);
            endLocalTime = LocalTime.parse(endTime.getText(), DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }

        if (title.getCharacters().isEmpty() || description.getCharacters().isEmpty() || type.getCharacters().isEmpty() || location.getCharacters().isEmpty() ||
                startDate.getValue() == null || startLocalTime == null || endDate.getValue() == null || endLocalTime == null ||
                customer.getSelectionModel().selectedItemProperty().isNull().get() || user.getSelectionModel().selectedItemProperty().isNull().get() || contact.getSelectionModel().selectedItemProperty().isNull().get()) {

            DialogController.contentText = ResourceBundle.getBundle("lang").getString("appointment.details.incomplete");
            javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("error"), Modality.APPLICATION_MODAL);

        } else {

            LocalDateTime start = LocalDateTime.of(startDate.getValue(), startLocalTime);
            LocalDateTime end = LocalDateTime.of(endDate.getValue(), endLocalTime);

            Appointment appointment = new Appointment(Integer.parseInt(id.getText()), title.getText(), description.getText(), location.getText(), type.getText(), start, end, customer.getSelectionModel().getSelectedItem().getCustomerId(), user.getSelectionModel().getSelectedItem().getUserId(), contact.getSelectionModel().getSelectedItem());

            AppointmentValidator appointmentValidator = new AppointmentValidator();

            if (appointmentValidator.validate(appointment)) {
                AppointmentDAO.updateAppointment(appointment);
                javaFXLoader.closeFXML(actionEvent);
            }
        }
    }

    /**
     * Closes current window without saving any changes.
     *
     * @param actionEvent ActionEvent to pass for closing window
     */
    @FXML
    public void onCancelButtonAction(ActionEvent actionEvent) {
        JavaFXLoader javaFXLoader = new JavaFXLoader();
        javaFXLoader.closeFXML(actionEvent);
    }
}