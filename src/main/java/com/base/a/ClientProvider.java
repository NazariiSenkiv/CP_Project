package com.base.a;

import com.base.a.Parser.JsonNameFileParser;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ClientProvider is a class that generates random client
 * and provides it to the next system that is passed in constructor
 * */
public class ClientProvider {
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
        var order = orderGenerator.createRandomOrder();
        return new Client(order, getRandomName(), LocalDateTime.now());
    }

    /**
     * Provides the client to the next system
     * */
    public void provideClient() {
        var client = generateClient();
        clientAcceptor.acceptClient(client);
    }
}
