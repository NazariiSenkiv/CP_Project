package com.base.a;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
//                try {
//                    Thread.sleep(AppConfig.clientSpawnDelaySeconds * 1000L);
//                    clientProvider.provideClient();
//                } catch (InterruptedException e) {
//                    System.out.println("Thread sleep exception");
//                }
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