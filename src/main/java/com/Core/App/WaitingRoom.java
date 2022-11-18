package com.Core.App;

import com.Core.Client.Client;
import com.Core.Order.Order;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaitingRoom {
    private static final Logger log = Logger.getLogger(WaitingRoom.class.getName());

    private List<Client> clients;
    private List<Order> completedOrder;

    public WaitingRoom() {
        clients = Collections.synchronizedList(new ArrayList<>());
        completedOrder = Collections.synchronizedList(new ArrayList<>());

        log.log(Level.INFO, "waiting room instance created");
    }

    public void addClient(Client client) {
        clients.add(client);
        log.log(Level.FINE, "Waiting room accepted: " + client.getName());
    }

    /**
     * Removes a client from waiting room with an order passed in the parameter
     * */
    public void acceptOrder(int orderId) {
        log.log(Level.FINE,"Client accepted order: "
                + clients.stream().filter(c -> c.getOrder().getId() == orderId).findFirst().get().getName()
                + "["+ LocalTime.now() +"]");

        completedOrder.add(clients.stream().filter(c -> c.getOrder().getId() == orderId).findFirst().get().getOrder());
        clients.removeIf(c -> c.getOrder().getId() == orderId);
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Order> getCompletedOrder() {
        return completedOrder;
    }
}
