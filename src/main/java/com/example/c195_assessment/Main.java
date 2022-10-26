package com.example.c195_assessment;

import com.example.c195_assessment.dao.*;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Main class for Java
 * extends abstract class Application
 *
 * @author Sheldon Handler
 */
public class Main extends Application {

    /**
     * Main method
     *
     * @param args args used in executing main method
     */
    public static void main(String[] args) {
        JDBC.makeConnection();
        try {
            UserDAO.setUserObservableList();
            CountryDAO.setCountryObservableList();
            DivisionDAO.setDivisionObservableList();
            ContactDAO.setContactObservableList();
            CustomerDAO.setCustomerObservableList();
            AppointmentDAO.setAppointmentObservableList();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        JavaFXLoader javaFXLoader = new JavaFXLoader();
        javaFXLoader.loadFXML("SignIn.fxml", ResourceBundle.getBundle("lang").getString("sign.in"), Modality.NONE);
    }
}