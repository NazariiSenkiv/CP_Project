package com.base.a;

import java.util.List;
import java.util.logging.Logger;

public class CompositeTask extends Task {
    private static final Logger log = Logger.getLogger(CompleteTask.class.getName());
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
