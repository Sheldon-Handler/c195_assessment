package com.example.c195_assessment;

import com.example.c195_assessment.controller.DialogController;
import com.example.c195_assessment.dao.AppointmentDAO;
import javafx.stage.Modality;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

/**
 * Java class for Validating Appointment
 */
public class AppointmentValidator {

    /**
     * Default constructor.
     */
    public AppointmentValidator() {
    }

    /**
     * Checks if Appointment is valid or not.
     *
     * @param appointment Appointment to validate
     * @return boolean whether Appointment is valid or not
     */
    public boolean validate(Appointment appointment) {

        LocalDateTime start = appointment.getStart().atZone(ZoneId.of("America/New_York")).toLocalDateTime();
        LocalDateTime end = appointment.getEnd().atZone(ZoneId.of("America/New_York")).toLocalDateTime();

        LocalDateTime businessOpening = LocalDateTime.of(start.atZone(ZoneId.of("America/New_York")).toLocalDate(), LocalTime.of(8, 0));
        LocalDateTime businessClosing = LocalDateTime.of(end.atZone(ZoneId.of("America/New_York")).toLocalDate(), LocalTime.of(22, 0));

        if (start.isBefore(businessOpening) || start.isAfter(businessClosing) || end.isBefore(businessOpening) || end.isAfter(businessClosing)) {
            DialogController.contentText = ResourceBundle.getBundle("lang").getString("schedule.outside.of.business.hours");

            JavaFXLoader javaFXLoader = new JavaFXLoader();
            javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("error"), Modality.APPLICATION_MODAL);

            return false;
        } else if (appointment.getStart().isAfter(appointment.getEnd()) || appointment.getStart().isEqual(appointment.getEnd())) {

            DialogController.contentText = ResourceBundle.getBundle("lang").getString("appointment.time.invalid");

            JavaFXLoader javaFXLoader = new JavaFXLoader();
            javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("error"), Modality.APPLICATION_MODAL);

            return false;

        } else if (timesOverlap(appointment.getStart(), appointment.getEnd(), appointment)) {

            DialogController.contentText = ResourceBundle.getBundle("lang").getString("appointment.times.overlap");

            JavaFXLoader javaFXLoader = new JavaFXLoader();
            javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("error"), Modality.APPLICATION_MODAL);

            return false;
        } else {

            return true;
        }
    }

    /**
     * Checks each item in appointmentObservableList for any overlap between the start and end LocalDateTime parameters.
     * Excludes the Appointment with same ID from the list of Appointment to check for overlapping LocalDateTime
     *
     * @param start       LocalDateTime of start for current Appointment checking if time overlaps
     * @param end         LocalDateTime of end for checking if time overlaps
     * @param appointment Appointment to exclude from checking based on whether appointmentId is identical
     * @return whether the date and time between start and end are available without overlapping Appointment
     */
    public boolean timesOverlap(LocalDateTime start, LocalDateTime end, Appointment appointment) {

        boolean flag = false;

        for (int i = 0; i < AppointmentDAO.appointmentObservableList.size(); i++) {
            if (start.equals(AppointmentDAO.appointmentObservableList.get(i).getStart()) || end.equals(AppointmentDAO.appointmentObservableList.get(i).getEnd())) {
                if (AppointmentDAO.appointmentObservableList.get(i).getAppointmentId() != appointment.getAppointmentId()) {
                    flag = true;
                }
                break;
            } else if (start.isAfter(AppointmentDAO.appointmentObservableList.get(i).getStart()) && start.isBefore(AppointmentDAO.appointmentObservableList.get(i).getEnd())) {
                if (AppointmentDAO.appointmentObservableList.get(i).getAppointmentId() != appointment.getAppointmentId()) {
                    flag = true;
                }
                break;
            } else if (end.isAfter(AppointmentDAO.appointmentObservableList.get(i).getStart()) && end.isBefore(AppointmentDAO.appointmentObservableList.get(i).getEnd())) {
                if (AppointmentDAO.appointmentObservableList.get(i).getAppointmentId() != appointment.getAppointmentId()) {
                    flag = true;
                }
                break;
            } else if (AppointmentDAO.appointmentObservableList.get(i).getStart().isAfter(start) && AppointmentDAO.appointmentObservableList.get(i).getEnd().isBefore(end)) {
                if (AppointmentDAO.appointmentObservableList.get(i).getAppointmentId() != appointment.getAppointmentId()) {
                    flag = true;
                }
                break;
            }
        }
        return flag;
    }
}