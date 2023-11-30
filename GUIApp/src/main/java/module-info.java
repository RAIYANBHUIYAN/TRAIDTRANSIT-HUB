module com.example.guiapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.dlsc.formsfx;
    requires java.desktop;


    opens com.example.guiapp to javafx.fxml;
    exports com.example.guiapp;
}