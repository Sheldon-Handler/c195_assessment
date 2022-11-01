package com.example.c195_assessment.controller;

import com.example.c195_assessment.dao.AppointmentDAO;
import com.example.c195_assessment.dto.NumberOfAppointments;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * <p>
 * Controller for "NumberOfAppointments.fxml"
 */
public class NumberOfAppointmentsController implements Initializable {

    /**
     * TableView for this report
     */
    @FXML
    protected TableView<NumberOfAppointments> tableView;

    /**
     * type of Appointment
     */
    @FXML
    protected TableColumn<NumberOfAppointments, String> type;

    /**
     * month of Appointment
     */
    @FXML
    protected TableColumn<NumberOfAppointments, String> month;

    /**
     * number of Appointment with corresponding type and month
     */
    @FXML
    protected TableColumn<NumberOfAppointments, Integer> numberOfAppointments;

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
        tableView.setItems(AppointmentDAO.numberOfAppointmentsObservableList);

        this.type.setCellValueFactory(new PropertyValueFactory<>(this.type.getId()));
        this.month.setCellValueFactory(new PropertyValueFactory<>(this.month.getId()));
        this.numberOfAppointments.setCellValueFactory(new PropertyValueFactory<>(this.numberOfAppointments.getId()));

    }
}
