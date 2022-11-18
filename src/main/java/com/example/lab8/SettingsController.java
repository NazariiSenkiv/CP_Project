package com.example.lab8;

import com.API.ApplicationController;
import com.Core.App.AppConfig;
import com.Core.App.ChiefWorkMode;
import com.Core.App.OrderGenerationStrategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private Spinner spinner_kasa;
    @FXML
    private Spinner spinner_chief;
    @FXML
    private Spinner spinner_pizza;
    @FXML
    private Button button_start;
    @FXML
    Spinner spinner_time_for_pizza;
    @FXML
    ComboBox ComboBox_generate;
    @FXML
    ComboBox ComboBox_cooking_mode;

    private ObservableList<String> listGenerate;
    private ObservableList<String> listMode;
    private ObservableList<String> listTime;
    private int numberOfKasa;
    private int numberOfChief;
    private int numberOfPizza;
    private int numberMinTime;
    private String indexGenerate;
    private String indexMode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullListGenerate();
        ComboBox_generate.setItems(listGenerate);
        ComboBox_generate.setValue(listGenerate.get(0));

        fullListMode();
        ComboBox_cooking_mode.setItems(listMode);
        ComboBox_cooking_mode.setValue(listMode.get(0));


        button_start.setOnAction(event -> {
            //зчитати тут дані і створити метод в якому вони будуть вноситися, після запустити потоки
            numberOfKasa = (Integer) spinner_kasa.getValue();
            numberOfChief = (Integer)spinner_chief.getValue();
            numberOfPizza = (Integer)spinner_pizza.getValue();
            numberMinTime = (Integer) spinner_time_for_pizza.getValue();
            indexGenerate = (String) ComboBox_generate.getValue();
            indexMode = (String) ComboBox_cooking_mode.getValue();

            setSettings();

            ApplicationController treads = ApplicationController.getInstance();
            treads.init();
            treads.run();

            button_start.getScene().getWindow().hide();
            runPage2();

        });
    }

    private void setSettings() {
        AppConfig.paydesksCount = numberOfKasa;
        AppConfig.chiefsCount = numberOfChief;
        AppConfig.numberOfPizzas = numberOfPizza;
        AppConfig.itemCookingMinTime = numberMinTime;

        switch (indexMode){
            case "Одиночний"->
                AppConfig.chiefWorkMode = ChiefWorkMode.SINGLE;

            case "Конвеєр"->
                AppConfig.chiefWorkMode = ChiefWorkMode.CONVEYOR;

        }

        switch (indexMode){
            case "RANDOM_ORDER":
                AppConfig.orderGenerationStrategy = OrderGenerationStrategy.RANDOM_ORDER;
                break;
            case "COMPLEX_ORDER":
                AppConfig.orderGenerationStrategy = OrderGenerationStrategy.COMPLEX_ORDER;
                break;
            case "SIMPLE_ORDER":
                AppConfig.orderGenerationStrategy = OrderGenerationStrategy.SIMPLE_ORDER;
                break;
            case "SUPER_COMPLEX_ORDER":
                AppConfig.orderGenerationStrategy = OrderGenerationStrategy.SUPER_COMPLEX_ORDER;
                break;
        }
    }


    public void runPage2(){

        Stage stage = new Stage();
        try {
            Scene scene = new Scene(new FXMLLoader(Resources.getInstance().getResource(1)).load(), 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fullListGenerate(){
        List<String> genetateList = new ArrayList<>();
        genetateList.add(OrderGenerationStrategy.RANDOM_ORDER.toString());
        genetateList.add(OrderGenerationStrategy.COMPLEX_ORDER.toString());
        genetateList.add(OrderGenerationStrategy.SIMPLE_ORDER.toString());
        genetateList.add(OrderGenerationStrategy.SUPER_COMPLEX_ORDER.toString());

        listGenerate = FXCollections.observableList(genetateList);
    }

    public void fullListMode(){
        List<String> mode = new ArrayList<>();
        mode.add("Одиночний");
        mode.add("Конвеєр");

        listMode = FXCollections.observableList(mode);
    }

    public void fullListTime(){
        List<String> time = new ArrayList<>();
        time.add("1");
        time.add("2");
        time.add("3");
        time.add("4");

        listTime = FXCollections.observableList(time);
    }
}