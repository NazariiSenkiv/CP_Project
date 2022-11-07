package com.base.a.Kitchen;

import com.base.a.Client.Client;
import com.base.a.Client.ClientAcceptor;
import com.base.a.Order.Order;
import com.base.a.AppCore.PaydeskManager;
import com.base.a.AppCore.WaitingRoom;

/**
 * Pizzeria is a class that is the
 * communication layer between the subsystems
 * */
public class Pizzeria implements ClientAcceptor {
    private final PaydeskManager paydeskManager;
    private KitchenFacade kitchenFacade;
    private final WaitingRoom waitingRoom;

    public Pizzeria() {
        paydeskManager = new PaydeskManager(this);
        waitingRoom = new WaitingRoom();
    }

    public void setKitchenFacade(KitchenFacade kitchenFacade) {
        this.kitchenFacade = kitchenFacade;
    }

    @Override
    public void acceptClient(Client client) {
        paydeskManager.addClient(client);
    }

    public void sendClientToWaitingRoom(Client client) {
        waitingRoom.addClient(client);
    }

    public void update() {
        paydeskManager.updatePaydesks();
    }

    public void acceptCompletedOrder(int id) {
        waitingRoom.acceptOrder(id);
    }

    public void sendOrderToKitchen(Order order) {
        kitchenFacade.acceptOrder(order);
    }
}
