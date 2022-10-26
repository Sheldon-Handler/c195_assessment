package com.example.c195_assessment.controller;

import com.example.c195_assessment.Appointment;
import com.example.c195_assessment.Contact;
import com.example.c195_assessment.JavaFXLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.c195_assessment.dao.AppointmentDAO.*;

public class AppointmentsController implements Initializable {

    @FXML
    protected RadioButton weekRadioButton, monthRadioButton;

    /**
     * appointmentTableView of Appointment
     */
    @FXML
    protected TableView<Appointment> appointmentTableView;

    /**
     * appointmentId in appointmentTableView
     */
    @FXML
    protected TableColumn<Appointment, Integer> appointmentId, /**
     * ID of Customer in appointmentTableView
     */
    customerId, /**
     * userId in appointmentTableView
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

        appointmentTableView.setItems(appointmentObservableListSortedByWeek);

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
    }

    /**
     * Method runs when Add Button is clicked.
     * Opens new "AddAppointment.fxml" window.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    protected void onAppointmentsAddButtonClick(ActionEvent actionEvent) {
        JavaFXLoader javaFXLoader = new JavaFXLoader();
        javaFXLoader.loadFXML("AddAppointment.fxml", ResourceBundle.getBundle("lang").getString("add.appointment"), Modality.APPLICATION_MODAL);
    }

    /**
     * Method run when Modify Button is clicked.
     * Opens new "EditAppointment.fxml" window.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onAppointmentsEditButtonClick(ActionEvent actionEvent) {
        EditAppointmentController.appointment = appointmentTableView.getSelectionModel().getSelectedItem();
        JavaFXLoader javaFXLoader = new JavaFXLoader();
        javaFXLoader.loadFXML("EditAppointment.fxml", ResourceBundle.getBundle("lang").getString("edit.appointment"), Modality.APPLICATION_MODAL);
    }

    /**
     * Method run when Delete Button is clicked.
     * Deletes selected item in appointmentsTable.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onAppointmentsDeleteButtonClick(ActionEvent actionEvent) {
        Appointment appointment = appointmentTableView.getSelectionModel().getSelectedItem();
        try {
            deleteAppointment(appointment);
            DialogController.contentText = ResourceBundle.getBundle("lang").getString("appointment.deleted") + " " + appointment.getAppointmentId();
            JavaFXLoader javaFXLoader = new JavaFXLoader();
            javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("deleted"), Modality.APPLICATION_MODAL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onWeekRadioButtonAction(ActionEvent actionEvent) {
        appointmentTableView.setItems(appointmentObservableListSortedByWeek);
        appointmentTableView.refresh();
    }

    @FXML
    public void onMonthRadioButtonAction(ActionEvent actionEvent) {
        appointmentTableView.setItems(appointmentObservableListSortedByMonth);
        appointmentTableView.refresh();
    }
}
