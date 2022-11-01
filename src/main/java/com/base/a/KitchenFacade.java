package com.base.a;

public class KitchenFacade {

    private Pizzeria pizzeria;
    private OrdersCollector ordersCollector;
    private OrdersUnpacker ordersUnpacker;
    private Kitchen kitchen;

    public KitchenFacade(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;

        ordersCollector = new OrdersCollector(this);
        ordersUnpacker = new OrdersUnpacker(this);
        kitchen = new Kitchen(this);
    }

    public void sendCompletedOrder(int id) {
        pizzeria.acceptCompletedOrder(id);
    }

    public void acceptOrder(Order order) {
        ordersCollector.createOrderBox(order);

        var pizzasList = ordersUnpacker.unpack(order);
        kitchen.addPizzas(pizzasList);
    }

    public void update() {
        kitchen.update();
    }

    public void passPizza(Pizza pizza) {
        ordersCollector.accept(pizza);
    }
}
