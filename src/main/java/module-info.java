module com.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    exports com.example.tourplanner.UI.View.controller;
    opens com.example.tourplanner.UI.View.controller to javafx.fxml;

    exports com.example.tourplanner.BL.models;
}