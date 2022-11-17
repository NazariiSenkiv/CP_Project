package com.base.a.Core.Client;

import com.base.a.Core.Order.Order;

import java.time.LocalDateTime;

public class Client {
    private String name;
    private LocalDateTime waitingStartTime;
    private Order order;

    public Client(Order order, String name, LocalDateTime waitingStartTime) {
        this.order = order;
        this.name = name;

        this.waitingStartTime = waitingStartTime;
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
