package com.base.a;

import java.util.List;

public class CompositeTask extends Task {
    private List<Task> previousTasks;

    public CompositeTask(String name, int secondsToComplete, List<Task> previousTasks) {
        super(name, secondsToComplete);
        this.previousTasks = previousTasks;
    }

    @Override
    public boolean isAvailable() {
        return previousTasks.stream().allMatch(Task::isCompleted);
    }
}
