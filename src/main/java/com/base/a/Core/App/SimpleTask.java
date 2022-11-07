package com.base.a.Core.App;

public class SimpleTask extends Task{

    public SimpleTask(String name, int secondsToComplete) {
        super(name, secondsToComplete);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
