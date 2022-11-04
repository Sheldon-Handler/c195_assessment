package com.example.c195_assessment.controller;

import com.example.c195_assessment.converter.ContactStringConverter;
import com.example.c195_assessment.dao.AppointmentDAO;
import com.example.c195_assessment.dao.ContactDAO;
import com.example.c195_assessment.dto.Appointment;
import com.example.c195_assessment.dto.Contact;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * JavaFX FXML Controller class
 * Controller for "ContactSchedule.fxml"
 */
public class ContactScheduleController implements Initializable {


    /**
     * ComboBox for selecting Contact to filter appointmentTableView by
     */
    @FXML
    protected ComboBox<Contact> contactComboBox;

    /**
     * TableView of Appointment to display appointments for selected Contact
     */
    @FXML
    protected TableView<Appointment> appointmentTableView;

    /**
     * ID of Appointment in appointmentTableView
     */
    @FXML
    protected TableColumn<Appointment, Integer> appointmentId, /**
     * ID of Customer in appointmentTableView
     */
    customerId,
    /**
     * ID of User in appointmentTableView
     */
    userId;

    /**
     * title of Appointment in appointmentTableView
     */
    @FXML
    protected TableColumn<Appointment, String> title, /**
     * type of Appointment in appointmentTableView
     */
    type, /**
     * description of Appointment in appointmentTableView
     */
    description;

    /**
     * start of Appointment in appointmentTableView
     */
    @FXML
    protected TableColumn<Appointment, LocalDateTime> start, /**
     * end of Appointment in appointmentTableView
     */
    end;

    /**
     * FilteredList for Appointment filtered by selected item in contactComboBox
     */
    private FilteredList<Appointment> appointmentFilteredList = new FilteredList<>(AppointmentDAO.appointmentObservableListSortedByStart);

    /**
     * Default constructor.
     */
    public ContactScheduleController() {
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

        appointmentTableView.setItems(appointmentFilteredList);

        this.appointmentId.setCellValueFactory(new PropertyValueFactory<>(this.appointmentId.getId()));
        this.title.setCellValueFactory(new PropertyValueFactory<>(this.title.getId()));
        this.description.setCellValueFactory(new PropertyValueFactory<>(this.description.getId()));
        this.type.setCellValueFactory(new PropertyValueFactory<>(this.type.getId()));
        this.start.setCellValueFactory(new PropertyValueFactory<>(this.start.getId()));
        this.end.setCellValueFactory(new PropertyValueFactory<>(this.end.getId()));
        this.customerId.setCellValueFactory(new PropertyValueFactory<>(this.customerId.getId()));
        this.userId.setCellValueFactory(new PropertyValueFactory<>(this.userId.getId()));

        contactComboBox.setConverter(new ContactStringConverter());
        contactComboBox.setItems(ContactDAO.contactObservableList);
    }

    /**
     * Runs onAction() of contactComboBox.
     * Lambda expression: if contactComboBox is empty, it sets appointmentTableView to display all Appointments.
     * Lambda expression: If an item in contactComboBox is selected, it filters appointmentTableView to only display appointments with selected Contact.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onContactComboBoxAction(ActionEvent actionEvent) {

        if (contactComboBox.getSelectionModel().isEmpty()) {
            // Sets the appointmentFilteredList to contain all Appointment since the ComboBox contactComboBox is empty.
            appointmentFilteredList.setPredicate(appointment -> true);
        } else {
            // This statement sets the FilteredList to only display appointments with the Contact field matching
            // the Contact selected in contactComboBox.
            appointmentFilteredList.setPredicate(appointment -> {
                if (appointment.getContact().equals(contactComboBox.getSelectionModel().getSelectedItem())) {
                    return true;
                } else {
                    return false;
                }
            });
        }
    }
}