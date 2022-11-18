package com.example.lab8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzeriaApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var resources = Resources.getInstance();
        resources.addResource(PizzeriaApplication.class.getResource("page1.fxml"));
        resources.addResource(PizzeriaApplication.class.getResource("page2.fxml"));
        resources.addResource(PizzeriaApplication.class.getResource("page3.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(resources.getResource((0)));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Pizzeria");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}