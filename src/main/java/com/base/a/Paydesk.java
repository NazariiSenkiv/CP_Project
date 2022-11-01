package com.base.a;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Paydesk {
    private int number;
    private PaydeskManager paydeskManager;
    private Client servingClient;
    private LocalTime orderingEndTime = LocalTime.now();

    public Paydesk(PaydeskManager paydeskManager, int number) {
        this.number = number;
        this.paydeskManager = paydeskManager;
    }

    public void notifyPaydeskManager() {
        paydeskManager.update(this);
    }

    public synchronized void serveClient(Client servingClient) {
        if (servingClient == null) {
            return;
        }

        LocalTime orderingStartTime = LocalTime.now();
        // TODO: remove
        System.out.println("["+number+"]Accepted client: " + servingClient.getName()+", time:"+ orderingStartTime);

        this.servingClient = servingClient;
        orderingEndTime = orderingStartTime.plusSeconds(AppConfig.orderTimeInSeconds); //TODO: add random deviation
    }

    public synchronized void update() {
        if (LocalTime.now().compareTo(orderingEndTime) >= 0) {
            if (servingClient != null) {
                //TODO: remove
                System.out.println("["+number+"]Released client: " + servingClient.getName()+", time:"+ LocalTime.now());
                //

                paydeskManager.sendClientToWaitingRoom(servingClient);

                paydeskManager.sendOrderToKitchen(servingClient.getOrder());

                servingClient = null;
            }
            notifyPaydeskManager();
        }
    }
}
