package com.example.lab8;

import com.API.ApplicationController;
import com.Core.Client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Page2Controller implements Initializable {
    @FXML
    Button button_kitchen;
    @FXML
    TableView main_table;
    @FXML
    Button button_showMoreAboutOrder;
    @FXML
    TableView infoOrder_table;
    @FXML
    Spinner spinner_id;

    private ObservableList<ClientDTO> clientsForTable;
    private ObservableList<InfoOrderDTO> infoOrder;

    private Timer timer;
    private UpdateListClientsTimer timerTask;
    private int idOrderForMoreInfo;
    private ClientDTO clientInfo;
    private List<Client> allClients;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createTableClients();
        startTimer();

        button_kitchen.setOnAction(event -> {
            button_kitchen.getScene().getWindow().hide();

            runPage3();
        });

        button_showMoreAboutOrder.setOnAction(event->{
            idOrderForMoreInfo = (Integer)spinner_id.getValue();

            if(infoOrder_table.getItems() != null)
                infoOrder_table.getItems().clear();
            fillOrderInfo();

            infoOrder_table.setItems(infoOrder);
            createTableOrderInfo();


        });
    }

    public void runPage3(){
        Stage stage = new Stage();
        try {
            Scene scene = new Scene(new FXMLLoader(Resources.getInstance().getResource(2)).load(), 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTableClients(){
        TableColumn<ClientDTO, String> nameColom = new TableColumn<ClientDTO, String>("Клієнт");
        nameColom.setMinWidth(194.39998817443848);
        nameColom.setSortable(false);
        nameColom.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("name"));
        main_table.getColumns().add(nameColom);

        TableColumn<ClientDTO, Integer> idColom = new TableColumn<ClientDTO, Integer>("id");
        idColom.setMinWidth(109.5999755859375);
        idColom.setCellValueFactory(new PropertyValueFactory<ClientDTO, Integer>("idOrder"));
        main_table.getColumns().add(idColom);

        TableColumn<ClientDTO, String> statusColom = new TableColumn<ClientDTO, String>("Статус");
        statusColom.setMinWidth(180.00006103515625);
        statusColom.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("status"));
        main_table.getColumns().add(statusColom);
    }

    public void createTableOrderInfo(){
        TableColumn<InfoOrderDTO, String> namePizzaColom = new TableColumn<InfoOrderDTO, String>("Піца");
        namePizzaColom.setMinWidth(127.9998779296875);
        namePizzaColom.setCellValueFactory(new PropertyValueFactory<InfoOrderDTO, String>("namePizza"));
        infoOrder_table.getColumns().add(namePizzaColom);

        TableColumn<InfoOrderDTO, Integer> numberOfPizzaColom = new TableColumn<InfoOrderDTO, Integer>("Кількість");
        numberOfPizzaColom.setMinWidth(100.800048828125);
        numberOfPizzaColom.setEditable(false);
        numberOfPizzaColom.setCellValueFactory(new PropertyValueFactory<InfoOrderDTO, Integer>("numberOfPizza"));
        infoOrder_table.getColumns().add(numberOfPizzaColom);
    }

    public void startTimer(){
        timer = new Timer();
        timerTask = new UpdateListClientsTimer(this);


        timer.scheduleAtFixedRate(timerTask, 1000/*ms*/, 1000);

    }

    public void setClientsForTable(List<ClientDTO> clients){
        clientsForTable = FXCollections.observableList(clients);
        main_table.getItems().clear();
        main_table.setItems(clientsForTable);
    }

    public void fillOrderInfo(){
        ApplicationController applicationController;
        applicationController = ApplicationController.getInstance();
        var data = applicationController.getData();
        var pizzeria = applicationController.getPizzeria();
        try {
            var cl = data.getWaitingClient().stream().filter(e -> e.getOrder().getId() == idOrderForMoreInfo).toList().get(0);

            List<InfoOrderDTO> listInfo = new ArrayList<>();
            var pizzas = cl.getOrder().getPizzas().keySet();
            for (var i : pizzas) {
                listInfo.add(new InfoOrderDTO(i.getName(), cl.getOrder().getPizzas().get(i)));
            }
            infoOrder = FXCollections.observableList(listInfo);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
