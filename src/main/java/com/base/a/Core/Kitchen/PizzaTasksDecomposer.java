package com.base.a.Kitchen;

import com.base.a.AppCore.Task;

import java.util.List;

public abstract class PizzaTasksDecomposer {
    public abstract List<Task> decompose(Pizza p, Kitchen kitchen);
}
