package com.Core.App;

import com.Core.Client.Client;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Paydesk {
    private static final Logger log = Logger.getLogger(Paydesk.class.getName());

    private int number;
    private PaydeskManager paydeskManager;
    private Client servingClient;
    private LocalTime orderingEndTime = LocalTime.now();

    public Paydesk(PaydeskManager paydeskManager, int number) {
        this.number = number;
        this.paydeskManager = paydeskManager;
    }

    /**
     * Sends a message to the paydesk manager to serve other client
     * */
    public void notifyPaydeskManager() {
        paydeskManager.update(this);
    }

    /**
     * Receives another client to be served
     * */
    public synchronized void serveClient(Client servingClient) {
        if (servingClient == null) {
            return;
        }

        LocalTime orderingStartTime = LocalTime.now();

        log.log(Level.FINE, "Accepted client: " + servingClient.getName()+", time:"+ orderingStartTime);

        this.servingClient = servingClient;
        orderingEndTime = orderingStartTime.plusSeconds(AppConfig.orderTimeInSeconds); //TODO: add random deviation
    }

    /**
     * Controls paydesk in the thread
     * and checks if the client is served
     * */
    public synchronized void update() {
        if (LocalTime.now().compareTo(orderingEndTime) >= 0) {
            if (servingClient != null) {
                log.log(Level.FINE, "Released client: " + servingClient.getName()+", time:"+ LocalTime.now());


                paydeskManager.sendClientToWaitingRoom(servingClient);
                paydeskManager.sendOrderToKitchen(servingClient.getOrder());

                servingClient = null;
            }
            notifyPaydeskManager();
        }
    }

    public int getNumber() {
        return number;
    }

    public Client getServingClient() {
        return servingClient;
    }
}
