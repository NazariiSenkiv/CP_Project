package com.Core.App;

import com.Core.Kitchen.Pizza;

import java.util.List;

public class CompositeTask extends Task {
    private List<Task> previousTasks;

    public CompositeTask(String name, int secondsToComplete, List<Task> previousTasks, Pizza pizza) {
        super(name, secondsToComplete,pizza);
        this.previousTasks = previousTasks;
    }

    @Override
    public boolean isAvailable() {
        return previousTasks.stream().allMatch(Task::isCompleted);
    }
}
