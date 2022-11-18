package com.Core.Kitchen;

import com.Core.Parser.JsonNameFileParser;
import com.Core.App.Task;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chief {
    private static List<String> chiefNamesPool;
    private static int lastId = 0;
    private static final Logger log = Logger.getGlobal();

    private String name;
    private Kitchen kitchen;
    private Task currentTask;

    private int id;

    private boolean suspended;

    private LocalTime taskEndTime = LocalTime.now();

    public Chief(Kitchen kitchen) {
        this.kitchen = kitchen;

        chiefNamesPool = JsonNameFileParser.parse("chiefNames.json");

        Random random = new Random();
        name = chiefNamesPool.get(random.nextInt(chiefNamesPool.size()));

        id = lastId++;

        log.log(Level.FINE, "new Chief instance created : " + name);
    }

    public void suspend() {
        suspended = true;
    }

    public void enable() {
        suspended = false;
    }

    public void update() {
        if (LocalTime.now().compareTo(taskEndTime) >= 0 && !suspended) {
            if (currentTask != null) {
                currentTask.complete();
                log.log(Level.FINE, "Chief " + name + " completed task " + currentTask.getName());
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
        log.log(Level.FINE, "Chief " + name + " took task " + task.getName());

        currentTask = task;
        taskEndTime = now.plusSeconds(task.getSecondsToComplete());
    }

    public String getName() {
        return name;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public LocalTime getTaskEndTime() {
        return taskEndTime;
    }

    public int getId() {
        return id;
    }
    public String getStatus(){
        if(suspended == false)
            return "Працює";
        else
            return "Відпочиває";
    }
}
