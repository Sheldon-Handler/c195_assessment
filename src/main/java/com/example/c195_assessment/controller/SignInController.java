package com.example.c195_assessment.controller;

import com.example.c195_assessment.Appointment;
import com.example.c195_assessment.JavaFXLoader;
import com.example.c195_assessment.LogWriter;
import com.example.c195_assessment.User;
import com.example.c195_assessment.dao.AppointmentDAO;
import com.example.c195_assessment.dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * Controller for "SignIn.fxml"
 *
 * @author Sheldon Handler
 */
public class SignInController implements Initializable {

    /**
     * Label for displaying location
     */
    @FXML
    protected Label label;

    /**
     * TextField for Username
     */
    @FXML
    protected TextField usernameField;

    /**
     * TextField for password
     */
    @FXML
    protected TextField passwordField;

    /**
     * Login Button
     */
    @FXML
    protected Button loginButton;

    private User user;

    /**
     * Default constructor
     */
    public SignInController() {
    }

    /**
     * setting language based on system Locale
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(ZoneId.systemDefault().getId());
    }

    /**
     * Code to execute when Login Button is clicked.
     * Closes "SignIn.fxml" window and opens "Home.fxml" window.
     *
     * @param actionEvent ActionEvent passed from Button press
     */
    @FXML
    protected void onLoginButtonClick(ActionEvent actionEvent) {

        LogWriter logWriter = new LogWriter();

        for (int i = 0; i < UserDAO.userObservableList.size(); i++) {
            if (UserDAO.userObservableList.get(i).getUserName().equals(usernameField.getText()) && UserDAO.userObservableList.get(i).getUserName().equals(passwordField.getText())) {
                user = UserDAO.userObservableList.get(i);
            }
        }

        JavaFXLoader javaFXLoader = new JavaFXLoader();
        if (user != null) {
            javaFXLoader.closeFXML(actionEvent);
            logWriter.addToLog("successful login of User_Name: " + usernameField.getText() + " on " + ZonedDateTime.now());
            javaFXLoader.loadFXML("Home.fxml", ResourceBundle.getBundle("lang").getString("home"), Modality.NONE);
            notification(user);
        } else {
            DialogController.contentText = ResourceBundle.getBundle("lang").getString("incorrect.login");
            logWriter.addToLog("failed login attempt of User_Name: " + usernameField.getText() + " on " + ZonedDateTime.now());
            javaFXLoader.loadFXML("Dialog.fxml", "Incorrect Login", Modality.APPLICATION_MODAL);
        }
    }

    /**
     * creates a notification DialogPane if there is an Appointment within 15 minutes before LocalDateTime.now()
     */
    private void notification(User user) {
        Appointment appointment = null;
        try {
            appointment = AppointmentDAO.notification(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (appointment != null) {
            DialogController.contentText = ResourceBundle.getBundle("lang").getString("appointment.notification.content")
                    + " " + appointment.getStart() + " "
                    + ResourceBundle.getBundle("lang").getString("appointment.id")
                    + " = " + appointment.getAppointmentId();

            JavaFXLoader javaFXLoader = new JavaFXLoader();

            javaFXLoader.loadFXML("Dialog.fxml", ResourceBundle.getBundle("lang").getString("reminder"), Modality.APPLICATION_MODAL);
        }
    }
}