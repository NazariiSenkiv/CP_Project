package com.base.a.AppCore;

import com.base.a.Kitchen.Kitchen;
import com.base.a.Kitchen.Pizza;
import com.base.a.Kitchen.PizzaTasksDecomposer;

import java.util.List;

public class MultipleTasksDecomposer extends PizzaTasksDecomposer {
    @Override
    public List<Task> decompose(Pizza p, Kitchen kitchen) {
        int taskTime = AppConfig.itemCookingMinTime > 3 ? AppConfig.itemCookingMinTime / 3 : 1;

        var kneadDoughTask = new SimpleTask("knead the dough", taskTime);
        var prepareIngredientsTask = new SimpleTask("prepare the ingredients\n", taskTime);

        var bakePizzaTask =
                new CompositeTask("bake the pizza", taskTime, List.of(kneadDoughTask, prepareIngredientsTask));

        var completePizzaTask =
                new CompleteTask("complete pizza", 0, List.of(bakePizzaTask), kitchen, p);

        return List.of(kneadDoughTask, prepareIngredientsTask, bakePizzaTask, completePizzaTask);
    }
}
