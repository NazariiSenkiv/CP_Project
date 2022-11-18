package com.Core.Client;

import com.Core.App.AppConfig;
import com.Core.Order.OrderGenerator;
import com.Core.Parser.JsonNameFileParser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClientProvider is a class that generates random client
 * and provides it to the next system that is passed in constructor
 * */
public class ClientProvider {
    private static final Logger log = Logger.getGlobal();

    private OrderGenerator orderGenerator;
    private List<String> namesPool;
    private ClientAcceptor clientAcceptor;

    private String getRandomName() {
        Random random = new Random();
        return namesPool.get(random.nextInt(namesPool.size()));
    }

    public ClientProvider(ClientAcceptor clientAcceptor) {
        orderGenerator = new OrderGenerator();
        this.clientAcceptor = clientAcceptor;

        namesPool = JsonNameFileParser.parse(AppConfig.jsonNamesPath);
    }

    public Client generateClient() {
        var order = orderGenerator.createOrder();
        return new Client(order, getRandomName(), LocalDateTime.now());
    }

    /**
     * Provides the client to the next system
     * */
    public Client provideClient() {
        var client = generateClient();

        log.log(Level.FINE,"Spawned client: " + client.getName()
                + ", order: " + client.getOrder());

        clientAcceptor.acceptClient(client);

        return client;
    }
}
