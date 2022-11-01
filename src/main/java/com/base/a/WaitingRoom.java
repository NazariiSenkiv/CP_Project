package com.base.a;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WaitingRoom {
    private List<Client> clients;

    WaitingRoom() {
        clients = Collections.synchronizedList(new ArrayList<>());
    }

    public void addClient(Client client) {
        clients.add(client);

        // TODO: remove
        System.out.println("Waiting room accepted: " + client.getName());
        System.out.println("Waiting room: " + clients.stream().map(Client::getName).collect(Collectors.joining(", ")));
        //
    }

    public void acceptOrder(int orderId) {
        // TODO: remove
        System.out.println("Client accepted order: "
                + clients.stream().filter(c -> c.getOrder().getId() == orderId).findFirst().get().getName()
                + "["+ LocalTime.now() +"]");
        //

        clients.removeIf(c -> c.getOrder().getId() == orderId);
    }
}
