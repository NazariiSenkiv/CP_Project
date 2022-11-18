package com.API;

import com.Core.App.Paydesk;
import com.Core.Client.Client;
import com.Core.Kitchen.Chief;
import com.Core.Order.Order;

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

    public synchronized List<Client> getClientList() {
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
