package com.Core.Kitchen;

import com.Core.Client.Client;
import com.Core.Client.ClientAcceptor;
import com.Core.Order.Order;
import com.Core.App.PaydeskManager;
import com.Core.App.WaitingRoom;

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

    public final PaydeskManager getPaydeskManager() {
        return paydeskManager;
    }

    public final WaitingRoom getWaitingRoom() {
        return waitingRoom;
    }
}
