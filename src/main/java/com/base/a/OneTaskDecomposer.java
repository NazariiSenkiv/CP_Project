package com.base.a;

import java.util.ArrayList;
import java.util.List;

public class OneTaskDecomposer extends PizzaTasksDecomposer {

    @Override
    public List<Task> decompose(Pizza p, Kitchen kitchen) {
        var cookPizzaTask = new SimpleTask("cooking pizza", AppConfig.itemCookingMinTime);

        var completePizzaTask =
                new CompleteTask("complete pizza", 0, List.of(cookPizzaTask), kitchen, p);

        return List.of(cookPizzaTask, completePizzaTask);
    }
}
