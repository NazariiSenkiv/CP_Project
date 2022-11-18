package com.example.lab8;

import com.API.ApplicationController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TimerTask;

public class UpdateListClientsTimer extends TimerTask {
    private Page2Controller page2Controller;
    public UpdateListClientsTimer(Page2Controller page2Controller) {
        super();
        this.page2Controller = page2Controller;
    }
    @Override
    public void run() {
        try {
            List<ClientDTO> clients = new ArrayList<ClientDTO>();
            ApplicationController applicationController;
            applicationController = ApplicationController.getInstance();
            var data = applicationController.getData();
            var pizzeria = applicationController.getPizzeria();

            var waitingClientList = data.getWaitingClient().stream() //from waiting room
                    .map((e) -> {
                        return new ClientDTO(e.getName(), e.getOrder().getId(), "Очікує видачу замовлення", e);
                    }).toList();

            var waitingClientsInPaydesk = pizzeria.getPaydeskManager().getWaitingClients().stream()//paydesk
                    .map((e) -> {
                        return new ClientDTO(e.getName(), e.getOrder().getId(), "В черзі на касу", e);
                    }).toList();

            var allClients = data.getPaydeskList().stream()
                    .map((e) -> {
                        return new ClientDTO(e.getServingClient().getName(), e.getServingClient().getOrder().getId(), "Замовляє на касі", e.getServingClient());
                    }).toList();

            clients.addAll(waitingClientList);
            clients.addAll(waitingClientsInPaydesk);
            clients.addAll(allClients);
            clients.sort(Comparator.comparing(ClientDTO::getIdOrder));
            this.page2Controller.setClientsForTable(clients);
        }
        catch (Exception e){
            System.out.println("Waiting for clients!");
        }
    }
}
