package com.base.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaitingRoom {
    private List<Client> clients;

    public WaitingRoom() {
        clients = Collections.synchronizedList(new ArrayList<>());
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Removes a client from waiting room with an order passed in the parameter
     * */
    public void acceptOrder(int orderId) {
        clients.removeIf(c -> c.getOrder().getId() == orderId);
    }
}
