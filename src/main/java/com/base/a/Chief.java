package com.base.a;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chief {
    private String name;
    private Kitchen kitchen;
    private Task currentTask;

    private LocalTime taskEndTime = LocalTime.now();

    public Chief(Kitchen kitchen) {
        this.kitchen = kitchen;

        fillChiefNamesPool();
        Random random = new Random();
        name = chiefNamesPool.get(random.nextInt(chiefNamesPool.size()));
    }

    public void update() {
        if (LocalTime.now().compareTo(taskEndTime) >= 0) {
            if (currentTask != null) {
                currentTask.complete();

                // TODO: remove
                System.out.println("Chief " + name + " completed task " + currentTask.getName() + "["+LocalTime.now()+"]");
                //

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
        // TODO: remove
        System.out.println("Chief " + name + " took task " + task.getName());
        //

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
