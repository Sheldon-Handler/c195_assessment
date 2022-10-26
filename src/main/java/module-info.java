module com.example.c195_assessment {

    requires java.sql;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports com.example.c195_assessment to javafx.fxml, javafx.graphics, javafx.controls, javafx.base;
    opens com.example.c195_assessment to javafx.fxml, javafx.graphics, javafx.controls, javafx.base;
    exports com.example.c195_assessment.controller to javafx.fxml, javafx.graphics, javafx.controls, javafx.base;
    opens com.example.c195_assessment.controller to javafx.fxml, javafx.graphics, javafx.controls, javafx.base;
}