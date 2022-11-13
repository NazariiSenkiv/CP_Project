package com.base.a.API;

import com.base.a.Core.App.AppConfig;
import com.base.a.Core.App.Paydesk;
import com.base.a.Core.Client.Client;
import com.base.a.Core.Client.ClientProvider;
import com.base.a.Core.Kitchen.Chief;
import com.base.a.Core.Kitchen.KitchenFacade;
import com.base.a.Core.Kitchen.Pizzeria;
import com.base.a.Core.Order.Order;

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

            // TODO: remove
            System.out.println("Completed order: " + data.getCompletedOrders());
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

}
