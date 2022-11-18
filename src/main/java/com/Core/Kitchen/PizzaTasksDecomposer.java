package com.Core.Kitchen;

import com.Core.App.Task;

import java.util.List;

public abstract class PizzaTasksDecomposer {
    public abstract List<Task> decompose(Pizza p, Kitchen kitchen);
}
