package com.base.a;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Menu instance;
    private List<Pizza> pizzas = null;

    private Menu() {
        pizzas = JsonPizzaFileParser.parseFile(AppConfig.jsonPizzasPath);
    }

    // returns found pizza or null
    public Pizza getPizzaByName(String pizzaName) {
        return pizzas.stream().filter(p -> p.getName().equals(pizzaName)).findFirst().orElse(null);
    }

    public int getPizzaCount() {
        return pizzas.size();
    }

    // returns copy of pizza list
    public List<Pizza> getPizzas() {
        return new ArrayList<>(pizzas);
    }

    public static Menu getInstance() {
        Menu result = instance;

        if (result != null) {
            return result;
        }

        synchronized (Menu.class) {
            if (instance == null) {
                instance = new Menu();
            }
            return instance;
        }
    }
}
