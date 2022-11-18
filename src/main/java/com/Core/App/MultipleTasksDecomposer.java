package com.Core.App;

import com.Core.Kitchen.Kitchen;
import com.Core.Kitchen.Pizza;
import com.Core.Kitchen.PizzaTasksDecomposer;

import java.util.List;

public class MultipleTasksDecomposer extends PizzaTasksDecomposer {
    @Override
    public List<Task> decompose(Pizza p, Kitchen kitchen) {
        int taskTime = AppConfig.itemCookingMinTime > 3 ? AppConfig.itemCookingMinTime / 3 : 1;

        var kneadDoughTask = new SimpleTask("knead the dough", taskTime, p);
        var prepareIngredientsTask = new SimpleTask("prepare the ingredients\n", taskTime, p);

        var bakePizzaTask =
                new CompositeTask("bake the pizza", taskTime, List.of(kneadDoughTask, prepareIngredientsTask), p);

        var completePizzaTask =
                new CompleteTask("complete pizza", 0, List.of(bakePizzaTask), kitchen, p);

        return List.of(kneadDoughTask, prepareIngredientsTask, bakePizzaTask, completePizzaTask);
    }
}
