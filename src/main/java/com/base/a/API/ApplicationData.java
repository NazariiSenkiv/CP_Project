package com.base.a.API;

import com.base.a.Core.App.Paydesk;
import com.base.a.Core.Client.Client;
import com.base.a.Core.Kitchen.Chief;
import com.base.a.Core.Order.Order;

import java.util.ArrayList;
import java.util.List;

public class ApplicationData {
    private List<Chief> chiefInfoList;
    private List<Client> clientList;
    private List<Paydesk> paydeskList;
    private List<Client> waitingClient;
    private List<Order> completedOrders;

    public ApplicationData() {
        clientList = new ArrayList<>();
    }

    public void setChiefInfoList(List<Chief> chiefs) {
        chiefInfoList = chiefs;
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public void setPaydeskList(List<Paydesk> paydesks) {
        paydeskList = paydesks;
    }

    public void setWaitingClient(List<Client> waitingClient) {
        this.waitingClient = waitingClient;
    }

    public void setCompletedOrders(List<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }

    /**
     * If thread hasn't returned list than returns null
     * else returns chief list.
     * */
    public List<Chief> getChiefInfoList() {
        return chiefInfoList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Paydesk> getPaydeskList() {
        return paydeskList;
    }

    public List<Client> getWaitingClient() {
        return waitingClient;
    }

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }
}
