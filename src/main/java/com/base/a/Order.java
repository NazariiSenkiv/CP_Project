package com.base.a;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private int id;
    private Map<Pizza, Integer> pizzas;

    public Order(int id, Map<Pizza, Integer> pizzas) {
        this.id = id;
        this.pizzas = pizzas;
    }

    public int getId() {
        return id;
    }

    public Map<Pizza, Integer> getPizzas() {
        return new HashMap<Pizza, Integer>(pizzas);
    }

    @Override
    public String toString() {
        return pizzas.entrySet().stream().map(e-> "["+ e.getKey().getName() + "(" + e.getValue() + ")" +"]")
                .collect(Collectors.joining(", "));
    }
}
