package com.base.a;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

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
            throw new RuntimeException(e);//TODO: log error
        }
    }

    public void update(Paydesk paydesk) {
        try {
            if (waitingClients.size() > 0) {
                paydesk.serveClient(waitingClients.take());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);//TODO: log error
        }
    }

    // TODO: remove
    private LocalTime lastOutputTime = LocalTime.now();
    //
    public void updatePaydesks() {
        // TODO: remove
        if (ChronoUnit.SECONDS.between(lastOutputTime, LocalTime.now()) >= 1) {
            lastOutputTime = LocalTime.now();
            System.out.println("Waiting queue: " + waitingClients.stream().map(Client::getName).collect(Collectors.joining(", ")));
        }
        //

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
}
