package com.example.lab8;

import com.API.ApplicationController;
import com.Core.App.AppConfig;
import com.Core.App.ChiefWorkMode;
import com.Core.Kitchen.Chief;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

public class Page3Controller implements Initializable {
    @FXML
    Button button_kasa;
    @FXML
    Button button_stop;
    @FXML
    Button button_go;
    @FXML
    TableView table_chiefs;
    @FXML
    Spinner spinner_id;
    @FXML
    Label label_mode;

    private ObservableList<ChiefDTO> chiefsForTable;
    private Timer timer;
    private UpdateListChiefsTimer timerTask;
    private int idChief;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableChiefs();
        startTimer();

        label_mode.setText("Режим роботи:\n" + ((AppConfig.chiefWorkMode == ChiefWorkMode.CONVEYOR) ?
                                                    "Конвеєр":"Одиночний"));

        button_kasa.setOnAction(event -> {
            button_kasa.getScene().getWindow().hide();

            runPage2();
        });

        button_stop.setOnAction(event->{
            idChief = ((Integer)spinner_id.getValue()) - 1;
            try {
                stopChiefById();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });

        button_go.setOnAction(event->{
            idChief = ((Integer)spinner_id.getValue()) - 1;
            try {
                goChiefById();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });
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

    public void startTimer(){
        timer = new Timer();
        timerTask = new UpdateListChiefsTimer(this);
        timer.scheduleAtFixedRate(timerTask, 200/*ms*/, 200);
    }

    public void setChiefsForTable(List<ChiefDTO> chiefs){
        chiefsForTable = FXCollections.observableList(chiefs);
        //table_chiefs.getItems().clear();
        table_chiefs.setItems(chiefsForTable);
    }

    public void createTableChiefs(){
        TableColumn<ChiefDTO, Integer> idColom = new TableColumn<ChiefDTO, Integer>("№");
        idColom.setMinWidth(5.0);
        idColom.setMaxWidth(40.0);
        idColom.setCellValueFactory(new PropertyValueFactory<ChiefDTO, Integer>("id"));
        table_chiefs.getColumns().add(idColom);

        TableColumn<ChiefDTO, String> nameColom = new TableColumn<ChiefDTO, String>("Кухар");
        nameColom.setMinWidth(140.800048828125);
        nameColom.setSortable(false);
        nameColom.setCellValueFactory(new PropertyValueFactory<ChiefDTO, String>("name"));
        table_chiefs.getColumns().add(nameColom);

        TableColumn<ChiefDTO, String> pizzaColom = new TableColumn<ChiefDTO, String>("Піца");
        pizzaColom.setMinWidth(129.99993896484375);
        pizzaColom.setCellValueFactory(new PropertyValueFactory<ChiefDTO, String>("pizzaName"));
        table_chiefs.getColumns().add(pizzaColom);

        TableColumn<ChiefDTO, String> doColom = new TableColumn<ChiefDTO, String>("Дія");
        doColom.setMinWidth(128.79998779296875);
        doColom.setCellValueFactory(new PropertyValueFactory<ChiefDTO, String>("toDo"));
        table_chiefs.getColumns().add(doColom);

        TableColumn<ChiefDTO, String> statusColom = new TableColumn<ChiefDTO, String>("Статус");
        statusColom.setMinWidth(151.79998779296875);
        statusColom.setCellValueFactory(new PropertyValueFactory<ChiefDTO, String>("status"));
        table_chiefs.getColumns().add(statusColom);
    }

    public void stopChiefById(){
        ApplicationController applicationController;
        applicationController = ApplicationController.getInstance();
        var data = applicationController.getData();

         var chief = data.getChiefInfoList().stream()
                .filter(e-> e.getId() == idChief).toList().get(0);

         chief.suspend();
    }

    public void goChiefById(){
        ApplicationController applicationController;
        applicationController = ApplicationController.getInstance();
        var data = applicationController.getData();

        var chief = data.getChiefInfoList().stream()
                .filter(e-> e.getId() == idChief).toList().get(0);

        chief.enable();
    }
}
