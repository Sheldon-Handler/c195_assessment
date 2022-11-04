package com.example.c195_assessment.controller;

import com.example.c195_assessment.JavaFXLoader;
import com.example.c195_assessment.dao.AppointmentDAO;
import com.example.c195_assessment.dto.Appointment;
import com.example.c195_assessment.dto.Contact;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Controller for Appointments.fxml
 */
public class AppointmentsController implements Initializable {

    /**
     * RadioButton to prevent filtering
     */
    @FXML
    protected RadioButton noneRadioButton, /**
     * RadioButton to filter by week of Start
     */
    weekRadioButton, /**
     * RadioButton to filter by month of Start
     */
    monthRadioButton;

    /**
     * DatePicker for filtering Appointment
     */
    @FXML
    protected DatePicker datePicker;

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
     * FilteredList of Appointment
     */
    @FXML
    protected FilteredList<Appointment> appointmentFilteredList = new FilteredList<>(AppointmentDAO.appointmentObservableList);

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
            AppointmentDAO.deleteAppointment(appointment);
            DialogController.contentText = ResourceBundle.getBundle("lang").getString("appointment.deleted") + " " + appointment.getAppointmentId() + "\n" + ResourceBundle.getBundle("lang").getString("type") + ": " + appointment.getType();
            JavaFXLoader javaFXLoader = new JavaFXLoader();
            javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("deleted"), Modality.APPLICATION_MODAL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs onAction() when noneRadioButton is clicked.
     * Disables and clears datePicker, then sets appointmentTableView to show all Appointment in AppointmentDAO.appointmentObservableList
     * The lambda function sets appointmentFilteredList to display all Appointment in appointmentTableView.
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    protected void onNoneRadioButtonAction(ActionEvent actionEvent) {

        datePicker.setDisable(true);
        datePicker.setValue(null);

        appointmentFilteredList.setPredicate(appointment -> true);
    }

    /**
     * Runs when weekRadioButton is pressed.
     * Sets datePicker to not being disabled.
     * The lambda function sets appointmentFilteredList to only display the Appointment in the same week as the date selected in datePicker in appointmentTableView
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onWeekRadioButtonAction(ActionEvent actionEvent) {

        datePicker.setDisable(false);

        appointmentFilteredList.setPredicate(appointment -> {
            if (appointment.getStart().get(WeekFields.SUNDAY_START.weekOfYear()) == datePicker.getValue().get(WeekFields.SUNDAY_START.weekOfYear())) {
                return true;
            } else {
                return false;
            }
        });
    }

    /**
     * Runs when monthRadioButton is pressed.
     * Sets datePicker to not be disabled.
     * The lambda function sets appointmentFilteredList to only display the Appointment in the same month as the date selected in datePicker in appointmentTableView
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onMonthRadioButtonAction(ActionEvent actionEvent) {

        datePicker.setDisable(false);

        appointmentFilteredList.setPredicate(appointment -> {
            if (appointment.getStart().get(ChronoField.MONTH_OF_YEAR) == datePicker.getValue().get(ChronoField.MONTH_OF_YEAR)) {
                return true;
            } else {
                return false;
            }
        });
    }


    /**
     * Runs onAction() of datePicker.
     * Filters by selected RadioButton criteria to filter by using the LocalDate in the field if not null
     *
     * @param actionEvent ActionEvent to pass
     */
    @FXML
    public void onDatePickerAction(ActionEvent actionEvent) {

        if (weekRadioButton.isSelected()) {
            onWeekRadioButtonAction(actionEvent);
        } else if (monthRadioButton.isSelected()) {
            onMonthRadioButtonAction(actionEvent);
        }
    }
}
