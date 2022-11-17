package com.base.a;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrdersCollector {
    private static final Logger log = Logger.getLogger(OrdersCollector.class.getName());
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
