/**
 * module-info for c195_assessment
 */
module c195_assessment {
    requires java.sql;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.example.c195_assessment to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
    opens com.example.c195_assessment.controller to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
    opens com.example.c195_assessment.dto to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
}