package com.base.a;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrdersUnpacker {
    private static final Logger log = Logger.getLogger(OrdersUnpacker.class.getName());
    private KitchenFacade kitchenFacade;

    public OrdersUnpacker(KitchenFacade kitchenFacade) {
        this.kitchenFacade = kitchenFacade;
    }

    public List<Pizza> unpack(Order order) {
        var pizzasMap = order.getPizzas();

        var pizzasList = new ArrayList<Pizza>();

        for(var entry : pizzasMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                pizzasList.add(entry.getKey());
            }
        }

        return pizzasList;
    }
}
