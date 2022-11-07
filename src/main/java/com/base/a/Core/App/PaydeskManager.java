package com.base.a.Core.App;

import com.base.a.Core.Client.Client;
import com.base.a.Core.Kitchen.Pizzeria;
import com.base.a.Core.Order.Order;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * PaydeskManager is a class that controls
 * all paydesks in the application. It sends
 * client to waiting rooms, orders to kitchen and updates the states of the paydesks.
 * */
public class PaydeskManager {
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
    }

    public void addClient(Client client) {
        try {
            waitingClients.put(client);
        } catch (InterruptedException e) {
            System.out.println("Client isn't added");
        }
    }

    public void update(Paydesk paydesk) {
        try {
            if (waitingClients.size() > 0) {
                paydesk.serveClient(waitingClients.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Paydesk updating error");
        }
    }

    public void updatePaydesks() {
        for (var paydesk : paydesks) {
            paydesk.update();
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
}
