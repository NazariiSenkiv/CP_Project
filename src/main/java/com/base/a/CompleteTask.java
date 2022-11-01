package com.base.a;

import java.util.List;

public class CompleteTask extends CompositeTask {
    private Kitchen kitchen;
    private Pizza pizza;

    public CompleteTask(String name, int secondsToComplete,
                        List<Task> previousTasks,
                        Kitchen kitchen, Pizza pizza) {
        super(name, secondsToComplete, previousTasks);

        this.kitchen = kitchen;
        this.pizza = pizza;
    }

    @Override
    public void complete() {
        super.complete();
        kitchen.passPizza(pizza);
    }
}
