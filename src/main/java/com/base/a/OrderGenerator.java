package com.base.a;

import java.time.LocalTime;
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

    /** generates list of random pizzas.
     * if menu contains fewer pizza types,
     * than required,
     * throws RuntimeException
     */
    private List<Pizza> getRandomPizzaList(int count) throws RuntimeException {
        int menuSize = Menu.getInstance().getPizzaCount();
        var pizzasList = Menu.getInstance().getPizzas();

        if (count > menuSize) {
            throw new RuntimeException("Passed pizzas count bigger than existing in menu!(OrderGenerator.getRandomPizzaList)");
        }

        if (count == menuSize) {
            return pizzasList;
        }

        var rand = new Random();
        var randomSelectedPizzas = new ArrayList<Pizza>();

        for (int i = 0; i < count; i++) {
            var pizza = getRandomPizza(pizzasList);
            pizzasList.remove(pizza);

            randomSelectedPizzas.add(pizza);
        }

        return randomSelectedPizzas;
    }

    /**
     * Creates an order with one type of pizza with count from 1 to 2
     * @return Order with incremented id
     */
    public Order createSimpleOrder() {
        var randomPizza = getRandomPizza();

        Random random = new Random(LocalTime.now().getNano());
        int pizzasCount = random.nextInt(1, 3);

        var pizzaMap = new HashMap<Pizza, Integer>();
        pizzaMap.put(randomPizza, pizzasCount);

        return new Order(nextId++, pizzaMap);
    }
    /**
     * Creates an order with random types of pizza with count from 1 to 3
     * @return Order with incremented id
     */
    public Order createComplexOrder() throws RuntimeException {
        Random random = new Random(LocalTime.now().getNano());

        var randomPizzaList = getRandomPizzaList(random.nextInt(2, 5));

        var pizzaMap = new HashMap<Pizza, Integer>();
        for (var pizza : randomPizzaList) {
            pizzaMap.put(pizza, random.nextInt(1, 4));
        }

        return new Order(nextId++, pizzaMap);
    }
    /**
     * Creates an order with random types of pizza with count from 2 to 7
     * @return Order with incremented id
     */
    public Order createSuperComplexOrder() {
        Random random = new Random(LocalTime.now().getNano());

        var randomPizzaList = getRandomPizzaList(random.nextInt(4, 8));

        var pizzaMap = new HashMap<Pizza, Integer>();
        for (var pizza : randomPizzaList) {
            pizzaMap.put(pizza, random.nextInt(2, 8));
        }

        return new Order(nextId++, pizzaMap);
    }

    /**
     * Creates random order
     * @return with probability of 60% creates simple order;
     * with probability of 30% creates complex order;
     * with probability of 10% creates super complex order
     * */
    public Order createRandomOrder() {
        Random random = new Random(LocalTime.now().getNano());
        int probability = random.nextInt(101);

        Order order = null;

        if (probability < 60) { // probability 60%
            return createSimpleOrder();
        }

        if (probability < 90) { // probability 30%
            return createComplexOrder();
        }

        return createSuperComplexOrder(); // probability 10%
    }
}
