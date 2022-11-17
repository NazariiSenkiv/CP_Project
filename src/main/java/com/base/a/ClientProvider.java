package com.base.a;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientProvider {
    private static final Logger log = Logger.getGlobal();
    private OrderGenerator orderGenerator;
    private List<String> namesPool;
    private ClientAcceptor clientAcceptor;

    private void fillNamesPool() {
        namesPool = new ArrayList<String>();

        namesPool.add("Ivan");
        namesPool.add("Petro");
        namesPool.add("Serhii");
        namesPool.add("Nazar");
        namesPool.add("Vitalik");
        namesPool.add("Volodymyr");
        namesPool.add("Danylo");
        namesPool.add("Andrii");
        namesPool.add("Myhailo");
        namesPool.add("Vlad");
        namesPool.add("Olexandr");
        namesPool.add("Marek");
        namesPool.add("Tosik");
        namesPool.add("Anatolii");
        namesPool.add("Tolik");
        namesPool.add("Garrik");
        namesPool.add("Jamal"); // :)

        namesPool.add("Chrystyna");
        namesPool.add("Yana");
        namesPool.add("Veronika");
        namesPool.add("Viktoria");
        namesPool.add("Maria");
        namesPool.add("Galina");
        namesPool.add("Vira");
        namesPool.add("Mariana");
        namesPool.add("Lilia");
        namesPool.add("Jamala");
        namesPool.add("Emilia");
        namesPool.add("Nadia");
        namesPool.add("Oksana");
        namesPool.add("Tetiana");
        namesPool.add("Ivanna");
        namesPool.add("Iana");
    }
    private String getRandomName() {
        Random random = new Random();

        return namesPool.get(random.nextInt(namesPool.size()));
    }

    public ClientProvider(ClientAcceptor clientAcceptor) {
        orderGenerator = new OrderGenerator();
        this.clientAcceptor = clientAcceptor;

        fillNamesPool();
    }

    public Client generateClient() {
        var order = orderGenerator.createRandomOrder();

        return new Client(order, getRandomName(), LocalDateTime.now());
    }

    public void provideClient() {
        var client = generateClient();
        log.log(Level.FINE,"Spawned client: " + client.getName()
        + ", order: " + client.getOrder());

        clientAcceptor.acceptClient(client);
    }
}
