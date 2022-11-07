package com.base.a.Kitchen;

import com.base.a.Parser.JsonNameFileParser;
import com.base.a.AppCore.Task;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Chief {
    private static List<String> chiefNamesPool;

    private String name;
    private Kitchen kitchen;
    private Task currentTask;

    private LocalTime taskEndTime = LocalTime.now();

    public Chief(Kitchen kitchen) {
        this.kitchen = kitchen;

        chiefNamesPool = JsonNameFileParser.parse("chiefNames.json");

        Random random = new Random();
        name = chiefNamesPool.get(random.nextInt(chiefNamesPool.size()));
    }

    public void update() {
        if (LocalTime.now().compareTo(taskEndTime) >= 0) {
            if (currentTask != null) {
                currentTask.complete();
                currentTask = null;
            }

            takeNextTask();
        }
    }

    public void takeNextTask() {
        kitchen.giveTask(this);
    }

    public void setTask(Task task) {
        if (task == null) {
            return;
        }

        var now = LocalTime.now();

        currentTask = task;
        taskEndTime = now.plusSeconds(task.getSecondsToComplete());
    }
}
