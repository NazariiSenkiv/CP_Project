package com.base.a;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WaitingRoom {
    private static final Logger log = Logger.getLogger(WaitingRoom.class.getName());
    private List<Client> clients;

    WaitingRoom() {
        clients = Collections.synchronizedList(new ArrayList<>());
        log.log(Level.INFO, "waiting room instance created");
    }

    public void addClient(Client client) {
        clients.add(client);
        log.log(Level.FINE, "Waiting room accepted: " + client.getName());
    }

    public void acceptOrder(int orderId) {
        log.log(Level.FINE,"Client accepted order: "
                + clients.stream().filter(c -> c.getOrder().getId() == orderId).findFirst().get().getName()
                + "["+ LocalTime.now() +"]");
        //

        clients.removeIf(c -> c.getOrder().getId() == orderId);
    }
}
