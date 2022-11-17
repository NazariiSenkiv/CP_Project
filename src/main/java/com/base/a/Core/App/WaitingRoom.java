package com.base.a.Core.App;

import com.base.a.Core.Client.Client;
import com.base.a.Core.Order.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaitingRoom {
    private List<Client> clients;
    private List<Order> completedOrder;

    public WaitingRoom() {
        clients = Collections.synchronizedList(new ArrayList<>());
        completedOrder = Collections.synchronizedList(new ArrayList<>());
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Removes a client from waiting room with an order passed in the parameter
     * */
    public void acceptOrder(int orderId) {
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
