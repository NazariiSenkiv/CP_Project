package com.base.a;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chief {

    private static final Logger log = Logger.getGlobal();
    private String name;
    private Kitchen kitchen;
    private Task currentTask;

    private LocalTime taskEndTime = LocalTime.now();

    public Chief(Kitchen kitchen) {
        this.kitchen = kitchen;

        fillChiefNamesPool();
        Random random = new Random();
        name = chiefNamesPool.get(random.nextInt(chiefNamesPool.size()));
        log.log(Level.FINE, "new Chief instance created : " + name);
    }

    public void update() {
        if (LocalTime.now().compareTo(taskEndTime) >= 0) {
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

    private static List<String> chiefNamesPool;

    private void fillChiefNamesPool() {
        chiefNamesPool = new ArrayList<>();

        chiefNamesPool.add("Sanji");
        chiefNamesPool.add("Marchello");
        chiefNamesPool.add("Giorno");
        chiefNamesPool.add("Francisk");
        chiefNamesPool.add("Soma");
        chiefNamesPool.add("Petro");
        chiefNamesPool.add("Pavlo");
        chiefNamesPool.add("Andrii");
        chiefNamesPool.add("Mia");
        chiefNamesPool.add("Mario");
    }
}
