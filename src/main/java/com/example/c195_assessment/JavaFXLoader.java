package com.example.c195_assessment;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for managing loading JavaFX GUI windows with FXML files.
 *
 * @author Sheldon Handler
 */
public class JavaFXLoader {

    /**
     * Default constructor.
     */
    public JavaFXLoader() {
    }

    /**
     * Create new JavaFX window using specified FXML file.
     *
     * @param fxmlFile String of FXML file name to load
     * @param title    title of window
     * @param modality Modality of window
     */
    public void loadFXML(String fxmlFile, String title, Modality modality) {

        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile), ResourceBundle.getBundle("lang", Locale.ENGLISH));
            Scene scene = new Scene(fxmlLoader.load());
            stage.initModality(modality);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * Closes current window Stage obtained from actionEvent parameters.
     *
     * @param actionEvent ActionEvent to pass for getting the window to close
     */
    public void closeFXML(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
