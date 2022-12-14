package com.Core.App;

import com.Core.Client.Client;
import com.Core.Kitchen.Pizzeria;
import com.Core.Order.Order;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * PaydeskManager is a class that controls
 * all paydesks in the application. It sends
 * client to waiting rooms, orders to kitchen and updates the states of the paydesks.
 * */
public class PaydeskManager {
    private static final Logger log = Logger.getLogger(PaydeskManager.class.getName());

    private Pizzeria pizzeria;
    private final List<Paydesk> paydesks;
    private final LinkedBlockingQueue<Client> waitingClients;

    public PaydeskManager(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;

        paydesks = new ArrayList<>();
        waitingClients = new LinkedBlockingQueue<Client>();

        for (int i = 0; i < AppConfig.paydesksCount; ++i) {
            paydesks.add(new Paydesk(this, i+1));
        }

        log.log(Level.INFO, "new paydesk manager created");
    }

    public void addClient(Client client) {
        try {
            waitingClients.put(client);
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, e.toString(), e);
        }
    }

    public void update(Paydesk paydesk) {
        try {
            if (waitingClients.size() > 0) {
                paydesk.serveClient(waitingClients.take());
            }
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, e.toString(), e);
        }
    }

    public void updatePaydesks() {
        for (var paydesk : paydesks) {
            paydesk.update();
            log.log(Level.FINEST,"Waiting queue: " + waitingClients.stream().map(Client::getName).collect(Collectors.joining(", ")));
        }
    }

    public void sendClientToWaitingRoom(Client client) {
        pizzeria.sendClientToWaitingRoom(client);
    }

    public void sendOrderToKitchen(Order order) {
        pizzeria.sendOrderToKitchen(order);
    }

    public List<Paydesk> getPaydesks() {
        return paydesks;
    }

    public List<Client> getWaitingClients(){ return waitingClients.stream().toList(); }
}
