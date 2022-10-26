package com.example.c195_assessment.controller;

import com.example.c195_assessment.JavaFXLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.ButtonType.OK;

/**
 * FXML Controller class
 * Controller for "Dialog.fxml"
 *
 * @author Sheldon Handler
 */
public class DialogController implements Initializable {

    /**
     * String of dialogPane content
     */
    public static String contentText;
    /**
     * DialogPane of DialogController
     */
    @FXML
    protected DialogPane dialogPane;

    /**
     * Default constructor.
     */
    public DialogController() {
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
        dialogPane.setContentText(contentText);
        Button button = (Button) dialogPane.lookupButton(OK);

        // This expression sets the OK Button to close the current dialog window when pressed.
        button.setOnAction(event -> new JavaFXLoader().closeFXML(event));
    }
}
