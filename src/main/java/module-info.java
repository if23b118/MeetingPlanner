module com.example.meetingplanner {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.logging.log4j;
    requires java.naming;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires spring.session;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires java.persistence;
    requires kernel;
    requires layout;
    requires io;

    opens com.example.meetingplaner to javafx.fxml;
    exports com.example.meetingplaner;
    exports com.example.meetingplaner.UI.View.controller;
    opens com.example.meetingplaner.UI.View.controller to javafx.fxml;

    exports com.example.meetingplaner.BL.models;
}