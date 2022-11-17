package com.base.a;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
}
