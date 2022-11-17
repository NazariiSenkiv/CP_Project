package com.base.a;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class Kitchen {
    private static final Logger log = Logger.getLogger(Kitchen.class.getName());
    private KitchenFacade kitchenFacade;
    private List<Task> tasks;
    private PizzaTasksDecomposer pizzaTasksDecomposer;

    private List<Chief> chiefs;

    public Kitchen(KitchenFacade kitchenFacade) {
        this.kitchenFacade = kitchenFacade;

        tasks = Collections.synchronizedList(new ArrayList<>());

        chiefs = new ArrayList<>();
        for (int i = 0; i < AppConfig.chiefsCount; ++i) {
            chiefs.add(new Chief(this));
        }

        switch (AppConfig.chiefWorkMode) {
            case SINGLE -> pizzaTasksDecomposer = new OneTaskDecomposer();
            case CONVEYOR -> pizzaTasksDecomposer = new MultipleTasksDecomposer();
        }
    }

    public synchronized void addPizzas(List<Pizza> pizzaList) {
        var newTasks = new ArrayList<Task>();
        for (var pizza : pizzaList) {
            newTasks.addAll(pizzaTasksDecomposer.decompose(pizza, this));
        }
        tasks.addAll(newTasks);
    }
    public void passPizza(Pizza pizza) {
        kitchenFacade.passPizza(pizza);
    }

    public synchronized void giveTask(Chief chief) {
        Task firstAvailableTask = null;
        for (var task : tasks) {
            if (task.isAvailable()) {
                firstAvailableTask = task;
                break;
            }
        }

        if (firstAvailableTask != null) {
            tasks.remove(firstAvailableTask);

            chief.setTask(firstAvailableTask);
        }
    }

    public void update() {
        for (var chief : chiefs) {
            chief.update();
        }
    }
}
