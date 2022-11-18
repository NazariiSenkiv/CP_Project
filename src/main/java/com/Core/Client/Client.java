package com.Core.Client;

import com.Core.Order.Order;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class.getName());

    private String name;
    private LocalDateTime waitingStartTime;
    private Order order;

    public Client(Order order, String name, LocalDateTime waitingStartTime) {
        this.order = order;
        this.name = name;

        this.waitingStartTime = waitingStartTime;
        log.log(Level.FINE, "new Client instance created : " + name);

    }

    public String getName() {
        return name;
    }

    public LocalDateTime getWaitingStartTime() {
        return waitingStartTime;
    }

    public Order getOrder() {
        return order;
    }
}
