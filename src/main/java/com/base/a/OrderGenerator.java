package com.base.a;

import java.util.*;

public class OrderGenerator {
    private static int nextId = 0;
    private Pizza getRandomPizza() {
        var pizzasList = Menu.getInstance().getPizzas();

        return getRandomPizza(pizzasList);
    }
    private Pizza getRandomPizza(List<Pizza> pizzaList) {
        var rand = new Random();

        return pizzaList.get(rand.nextInt(pizzaList.size()));
    }

    // generates list of random pizzas.
    // if menu contains fewer pizza types, than required, throws RuntimeException
    private List<Pizza> getRandomPizzaList(int count) {

        int menuSize = Menu.getInstance().getPizzaCount();
        var pizzasList = Menu.getInstance().getPizzas();

        if (count > menuSize) {
            throw new RuntimeException("Passed pizzas count bigger than existing in menu!(OrderGenerator.getRandomPizzaList)");
        }
        else if (count == menuSize) {
            return pizzasList;
        }
        else {
            var rand = new Random();
            var randomSelectedPizzas = new ArrayList<Pizza>();

            for (int i = 0; i < count; i++) {
                var pizza = getRandomPizza(pizzasList);
                pizzasList.remove(pizza);

                randomSelectedPizzas.add(pizza);
            }

            return randomSelectedPizzas;
        }
    }

    public Order createSimpleOrder() {
        var randomPizza = getRandomPizza();

        Random random = new Random();
        int pizzasCount = random.nextInt(1, 3);

        var pizzaMap = new HashMap<Pizza, Integer>();
        pizzaMap.put(randomPizza, pizzasCount);

        return new Order(nextId++, pizzaMap);
    }
    public Order createComplexOrder() {
        Random random = new Random();

        var randomPizzaList = getRandomPizzaList(random.nextInt(2, 5));

        var pizzaMap = new HashMap<Pizza, Integer>();
        for (var pizza : randomPizzaList) {
            pizzaMap.put(pizza, random.nextInt(1, 4));
        }

        return new Order(nextId++, pizzaMap);
    }
    public Order createSuperComplexOrder() {
        Random random = new Random();

        var randomPizzaList = getRandomPizzaList(random.nextInt(4, 8));

        var pizzaMap = new HashMap<Pizza, Integer>();
        for (var pizza : randomPizzaList) {
            pizzaMap.put(pizza, random.nextInt(2, 8));
        }

        return new Order(nextId++, pizzaMap);
    }

    public Order createRandomOrder() {
        Random random = new Random();
        int probability = random.nextInt(101);

        Order order = null;

        if (probability < 60) {             // probability 60%
            order = createSimpleOrder();
        } else if (probability < 90) {      // probability 30%
            order = createComplexOrder();
        } else {                            // probability 10%
            order = createSuperComplexOrder();
        }

        return order;
    }
}
