package com.base.a;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    /**
     * id - unique number that identify order
     * */
    private int id;
    /**
     * Pizza - object
     * Integer - count
     * */
    private Map<Pizza, Integer> pizzas;

    public Order(int id, Map<Pizza, Integer> pizzas) {
        this.id = id;
        this.pizzas = pizzas;
    }

    public int getId() {
        return id;
    }

    /**
     * Creates a copy of pizza map
     * */
    public Map<Pizza, Integer> getPizzas() {
        return new HashMap<Pizza, Integer>(pizzas);
    }

    @Override
    public String toString() {
        return pizzas.entrySet().stream().map(e-> "["+ e.getKey().getName() + "(" + e.getValue() + ")" +"]")
                .collect(Collectors.joining(", "));
    }
}
