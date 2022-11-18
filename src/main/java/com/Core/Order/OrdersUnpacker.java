package com.Core.Order;

import com.Core.Kitchen.KitchenFacade;
import com.Core.Kitchen.Pizza;

import java.util.ArrayList;
import java.util.List;

public class OrdersUnpacker {
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
