module com.example.hospital {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hospital to javafx.fxml;
    exports com.example.hospital;
    exports patient;
    opens patient to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}