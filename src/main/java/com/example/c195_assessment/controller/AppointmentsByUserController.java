package com.example.c195_assessment.controller;

import com.example.c195_assessment.Appointment;
import com.example.c195_assessment.Contact;
import com.example.c195_assessment.User;
import com.example.c195_assessment.converter.UserStringConverter;
import com.example.c195_assessment.dao.AppointmentDAO;
import com.example.c195_assessment.dao.UserDAO;
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

public class AppointmentsByUserController implements Initializable {

    /**
     * ComboBox for choosing the User to view Appointment for
     */
    @FXML
    protected ComboBox<User> user;

    /**
     * TableView of Appointment to view Appointment for selected date
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
     * ID of user in appointmentTableView
     */
    userId;

    /**
     * title in appointmentsTable
     */
    @FXML
    protected TableColumn<Appointment, String> title, /**
     * description in appointmentTableView
     */
    description, /**
     * location in appointmentTableView
     */
    location, /**
     * type in appointmentTableView
     */
    type;

    /**
     * TableColumn for contact in appointmentTableView
     */
    @FXML
    protected TableColumn<Appointment, Contact> contact;

    /**
     * LocalDateTime variable for start in appointmentTableView
     */
    @FXML
    protected TableColumn<Appointment, LocalDateTime> start, /**
     * LocalDateTime variable for end in appointmentTableView
     */
    end;

    /**
     * FilteredList of Appointment filtered by Date
     */
    private FilteredList<Appointment> appointmentFilteredList = new FilteredList<>(AppointmentDAO.appointmentObservableList);

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
        this.location.setCellValueFactory(new PropertyValueFactory<>(this.location.getId()));
        this.contact.setCellValueFactory(new PropertyValueFactory<>(this.contact.getId()));
        this.type.setCellValueFactory(new PropertyValueFactory<>(this.type.getId()));
        this.start.setCellValueFactory(new PropertyValueFactory<>(this.start.getId()));
        this.end.setCellValueFactory(new PropertyValueFactory<>(this.end.getId()));
        this.customerId.setCellValueFactory(new PropertyValueFactory<>(this.customerId.getId()));
        this.userId.setCellValueFactory(new PropertyValueFactory<>(this.userId.getId()));

        user.setConverter(new UserStringConverter());
        user.setItems(UserDAO.userObservableList);
    }

    /**
     * Runs in FXML for user onAction()
     * Filters appointmentTableView by the User that is selected in ComboBox user.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onUserAction(ActionEvent actionEvent) {

        if (user.getSelectionModel().isEmpty()) {
            // Sets the appointmentFilteredList to contain all Appointment since the ComboBox User is empty.
            appointmentFilteredList.setPredicate(appointment -> true);
        } else {
            // Sets the appointmentFilteredList to only contain Appointment with userId matching user selected in ComboBox user.
            appointmentFilteredList.setPredicate(appointment -> {
                if (appointment.getUserId() == user.getSelectionModel().getSelectedItem().getUserId()) {
                    return true;
                } else {
                    return false;
                }
            });
        }
    }
}
