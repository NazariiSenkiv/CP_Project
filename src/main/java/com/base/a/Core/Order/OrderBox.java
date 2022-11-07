package com.base.a.Order;

import com.base.a.Kitchen.Pizza;

import java.util.Map;

public class OrderBox {
    private int orderId;
    Map<Pizza, Integer> pizzasToComplete;

    public OrderBox(Order order) {
        orderId = order.getId();
        pizzasToComplete = order.getPizzas();
    }

    private Map.Entry<Pizza, Integer> findPizzaEntry(Pizza pizza) {
        return pizzasToComplete.entrySet().stream()
                                          .filter(s->s.getKey().equals(pizza))
                                          .findFirst()
                                          .orElse(null);
    }

    public boolean needsPizza(Pizza pizza) {
        var foundPizzaEntry = findPizzaEntry(pizza);

        if (foundPizzaEntry == null) {
            return false;
        }

        return foundPizzaEntry.getValue() > 0;
    }

    public void addPizza(Pizza pizza) {
        var foundPizza = findPizzaEntry(pizza);

        if (foundPizza != null && foundPizza.getValue() > 0) {
            pizzasToComplete.put(pizza, pizzasToComplete.get(pizza) - 1);
        }
    }

    public boolean isCompleted() {
        return pizzasToComplete.entrySet().stream().allMatch(e->e.getValue()==0);
    }

    public int getOrderId() {
        return orderId;
    }
}
