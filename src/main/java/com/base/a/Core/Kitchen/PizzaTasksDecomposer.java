package com.base.a.Core.Kitchen;

import com.base.a.Core.App.Task;

import java.util.List;

public abstract class PizzaTasksDecomposer {
    public abstract List<Task> decompose(Pizza p, Kitchen kitchen);
}
