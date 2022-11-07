package com.base.a.Core.Order;

import com.base.a.Core.Kitchen.KitchenFacade;
import com.base.a.Core.Kitchen.Pizza;

import java.util.ArrayList;
import java.util.List;

public class OrdersCollector {
    private KitchenFacade kitchenFacade;
    private List<OrderBox> orderBoxes;

    public OrdersCollector(KitchenFacade kitchenFacade) {
        this.kitchenFacade = kitchenFacade;

        orderBoxes = new ArrayList<>();
    }

    public synchronized void createOrderBox(Order order) {
        orderBoxes.add(new OrderBox(order));
    }

    public synchronized void accept(Pizza pizza) {
        var firstOrderBox = orderBoxes.stream().filter(ob -> ob.needsPizza(pizza)).findFirst().orElse(null);

        if (firstOrderBox != null) {
            firstOrderBox.addPizza(pizza);

            if (firstOrderBox.isCompleted()) {
                kitchenFacade.sendCompletedOrder(firstOrderBox.getOrderId());
                orderBoxes.remove(firstOrderBox);
            }
        } else {
            throw new RuntimeException("There is not such pizza to complete");
        }
    }
}
