package com.base.a;

import com.base.a.Parser.JsonNameFileParser;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientProvider {
    private OrderGenerator orderGenerator;
    private List<String> namesPool;
    private ClientAcceptor clientAcceptor;

    private void fillNamesPool() {
        namesPool = JsonNameFileParser.parse(AppConfig.jsonNamesPath);
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

        // TODO: remove this
        System.out.println("["+ LocalTime.now() +"]"+"Spawned client: " + client.getName()
        + ", order: " + client.getOrder());

        clientAcceptor.acceptClient(client);
    }
}
