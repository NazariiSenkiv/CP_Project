package com.base.a;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompleteTask extends CompositeTask {
    private static final Logger log = Logger.getLogger(CompleteTask.class.getName());
    private Kitchen kitchen;
    private Pizza pizza;

    public CompleteTask(String name, int secondsToComplete,
                        List<Task> previousTasks,
                        Kitchen kitchen, Pizza pizza) {
        super(name, secondsToComplete, previousTasks);

        this.kitchen = kitchen;
        this.pizza = pizza;
        log.log(Level.FINE, "new CompleteTask instance created : " + name);
    }

    @Override
    public void complete() {
        super.complete();
        kitchen.passPizza(pizza);
        log.log(Level.FINE, "completed : " + this.getName());
    }
}
