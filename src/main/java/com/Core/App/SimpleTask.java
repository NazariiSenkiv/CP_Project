package com.Core.App;

import com.Core.Kitchen.Pizza;

public class SimpleTask extends Task{

    public SimpleTask(String name, int secondsToComplete, Pizza pizza) {
        super(name, secondsToComplete, pizza);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
