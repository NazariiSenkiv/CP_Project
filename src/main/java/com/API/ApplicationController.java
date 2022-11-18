package com.API;

import com.Core.App.AppConfig;
import com.Core.App.Paydesk;
import com.Core.Client.Client;
import com.Core.Client.ClientProvider;
import com.Core.Kitchen.Chief;
import com.Core.Kitchen.KitchenFacade;
import com.Core.Kitchen.Pizzeria;
import com.Core.Order.Order;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Controls all resources in the application.
 * */
public class ApplicationController {
    private Pizzeria pizzeria;
    private KitchenFacade kitchen;
    private ClientProvider clientProvider;

    private Thread clientProviderThread;
    private Thread pizzeriaThread;
    private Thread kitchenThread;

    private ApplicationData data;

    private static ApplicationController instance;

    private ApplicationController() {
        pizzeria = new Pizzeria();
        kitchen = new KitchenFacade(pizzeria);
        data = new ApplicationData();
        clientProvider = new ClientProvider(pizzeria);
        pizzeria.setKitchenFacade(kitchen);
    }

    /** Initializes the threads of the application */
    public void init() {
        clientProviderThread = new Thread(this::clientProviderProcedure);
        pizzeriaThread = new Thread(this::pizzeriaProcedure);
        kitchenThread = new Thread(this::kitchenProcedure);
    }

    /** Runs the threads of the application */
    public void run() {
        clientProviderThread.start();
        pizzeriaThread.start();
        kitchenThread.start();
    }

    private void clientProviderProcedure() {
        LocalTime lastTime = LocalTime.now();
        while (true) {
            if (ChronoUnit.SECONDS.between(lastTime, LocalTime.now()) >= AppConfig.clientSpawnDelaySeconds) {
                data.addClient(clientProvider.provideClient());
                lastTime = LocalTime.now();
            }
        }
    }

    private void pizzeriaProcedure() {
        while (true) {
            pizzeria.update();
            data.setPaydeskList(new ArrayList<>(pizzeria.getPaydeskManager().getPaydesks()));
            data.setWaitingClient(new ArrayList<>(pizzeria.getWaitingRoom().getClients()));
            data.setCompletedOrders(new ArrayList<>(pizzeria.getWaitingRoom().getCompletedOrder()));


        }
    }

    private void kitchenProcedure() {
        while (true) {
            kitchen.update();
            data.setChiefInfoList(new ArrayList<>(kitchen.getKitchen().getChiefs()));
        }
    }

    public ApplicationData getData() {
        return data;
    }

    public static ApplicationController getInstance() {
        if (instance == null) {
            instance = new ApplicationController();
        }

        return instance;
    }

    public Pizzeria getPizzeria(){ return pizzeria; }

}
