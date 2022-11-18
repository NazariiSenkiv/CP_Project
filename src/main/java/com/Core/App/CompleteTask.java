package com.Core.App;

import com.Core.Kitchen.Kitchen;
import com.Core.Kitchen.Pizza;

import java.util.List;

public class CompleteTask extends CompositeTask {
    private Kitchen kitchen;

    public CompleteTask(String name, int secondsToComplete,
                        List<Task> previousTasks,
                        Kitchen kitchen, Pizza pizza) {
        super(name, secondsToComplete, previousTasks, pizza);

        this.kitchen = kitchen;

    }

    @Override
    public void complete() {
        super.complete();
        kitchen.passPizza(pizza);
    }
}
