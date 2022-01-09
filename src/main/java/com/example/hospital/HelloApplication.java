package com.example.hospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #D4F1F4;");
        grid.setHgap(5); //horizontal gap in pixels => that's what you are asking for
        grid.setVgap(5); //vertical gap in pixels
        grid.setPadding(new Insets(5, 5, 5, 5));
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("hospital.JPG")));
        ImageView imageView = new ImageView(image);
        grid.add(imageView, 80, 0, 20, 117);

        //add textFields for a new patient
        //name:
        TextField clientName = new TextField();
        clientName.setPromptText("Name:");
        grid.add(clientName, 5, 0, 10, 10);
        //address
        TextField clientAddress = new TextField();
        clientAddress.setPromptText("Address:");
        grid.add(clientAddress, 20, 0, 10, 10);
        //phone number:
        TextField phoneNumber = new TextField();
        phoneNumber.setPromptText("Phone Number:");
        grid.add(phoneNumber, 5, 0, 10, 30);
        //date
        TextField age = new TextField();
        age.setPromptText("Age:");
        grid.add(age, 20, 0, 10, 30);
        //problem description
        TextField problemDescription = new TextField();
        problemDescription.setPromptText("Problem description:");
        problemDescription.setMaxHeight(100);
        grid.add(problemDescription, 5, 0, 10, 60);
        //age
        TextField dateApp = new TextField();
        dateApp.setPromptText("Date:");
        grid.add(dateApp, 20, 0, 10, 50);
        //appointment date
        Button addPatient = new Button("Add Patient");
        grid.add(addPatient, 20, 0 , 10, 70);
        Button updatePatient = new Button("Update Patient");
        grid.add(updatePatient, 30, 0 , 10, 70);

        Scene scene = new Scene(grid, 1200, 600);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}