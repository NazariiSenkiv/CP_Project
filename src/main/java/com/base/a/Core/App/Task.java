package com.base.a.Core.App;

import com.base.a.Core.Kitchen.Pizza;

public abstract class Task {
    private String name;
    private int secondsToComplete;
    private boolean completed = false;
    protected Pizza pizza;

    protected Task(String name, int secondsToComplete, Pizza pizza) {
        this.name = name;
        this.secondsToComplete = secondsToComplete;
        this.pizza = pizza;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void complete() {
        completed = true;
    }
    public abstract boolean isAvailable();

    public int getSecondsToComplete() {
        return secondsToComplete;
    }

    public String getPizzaName() {
        return pizza.getName();
    }

    public String getName() {
        return name;
    }
}
