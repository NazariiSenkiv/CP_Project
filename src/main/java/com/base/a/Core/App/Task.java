package com.base.a.Core.App;

public abstract class Task {
    private String name;
    private int secondsToComplete;
    private boolean completed = false;

    protected Task(String name, int secondsToComplete) {
        this.name = name;
        this.secondsToComplete = secondsToComplete;
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

    public String getName() {
        return name;
    }
}
