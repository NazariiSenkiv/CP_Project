package com.base.a;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.LogManager;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.logging.Logger;
import java.util.logging.LogManager;

class Main {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getLogManager();

        try {
            logManager.readConfiguration(new FileInputStream("./LogConfig.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Logger logger = Logger.getGlobal();
        logger.info("Logic kernel init");

        var menu = Menu.getInstance();

        var pizzeria = new Pizzeria();
        var kitchen = new KitchenFacade(pizzeria);
        pizzeria.setKitchenFacade(kitchen);

        var clientProvider = new ClientProvider(pizzeria);

        Thread clientProviderThread = new Thread(() -> {
            LocalTime lastTime = LocalTime.now();
            logger.info("client provider thread started");
            while (true) {
//                try {
//                    Thread.sleep(AppConfig.clientSpawnDelaySeconds * 1000L);
//                    clientProvider.provideClient();
//                } catch (InterruptedException e) {
//                    log.log(Level.SEVERE, e);
//                }
                if (ChronoUnit.SECONDS.between(lastTime, LocalTime.now()) >= AppConfig.clientSpawnDelaySeconds) {
                    clientProvider.provideClient();
                    lastTime = LocalTime.now();
                }
            }
        });

        Thread pizzeriaThread = new Thread(() -> {
            logger.info("new pizzeria thread started");
           while (true) {
               pizzeria.update();
           }
        });

        Thread kitchenThread = new Thread(() -> {
            logger.info("new kitchen thread started");
            while (true) {
                kitchen.update();
            }
        });

        clientProviderThread.start();
        pizzeriaThread.start();
        kitchenThread.start();
    }
}