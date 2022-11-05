package com.base.a;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

class Main {
    public static void main(String[] args) {
        var menu = Menu.getInstance();

        var pizzeria = new Pizzeria();
        var kitchen = new KitchenFacade(pizzeria);
        pizzeria.setKitchenFacade(kitchen);

        var clientProvider = new ClientProvider(pizzeria);

        Thread clientProviderThread = new Thread(() -> {
            LocalTime lastTime = LocalTime.now();
            while (true) {
                if (ChronoUnit.SECONDS.between(lastTime, LocalTime.now()) >= AppConfig.clientSpawnDelaySeconds) {
                    clientProvider.provideClient();
                    lastTime = LocalTime.now();
                }
            }
        });

        Thread pizzeriaThread = new Thread(() -> {
           while (true) {
               pizzeria.update();
           }
        });

        Thread kitchenThread = new Thread(() -> {
            while (true) {
                kitchen.update();
            }
        });

        clientProviderThread.start();
        pizzeriaThread.start();
        kitchenThread.start();
    }
}