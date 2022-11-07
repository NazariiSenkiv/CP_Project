package com.base.a.Kitchen;

import com.base.a.AppCore.AppConfig;
import com.base.a.AppCore.MultipleTasksDecomposer;
import com.base.a.AppCore.OneTaskDecomposer;
import com.base.a.AppCore.Task;


import java.util.*;

public class Kitchen {
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
