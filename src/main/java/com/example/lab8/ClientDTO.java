package com.example.lab8;

import com.Core.Client.Client;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClientDTO {
    public String name;
    public Integer idOrder;
    public String status;
    public Client client;

    public ClientDTO(){}

    public ClientDTO(String Name, int id, String Status, Client cl){
        name = Name;
        idOrder = id;
        status = Status;
        client = cl;
    }

    public int getIdOrder() {
        return idOrder;
    }
    public String getName(){ return name; }
    public String getStatus(){ return status; }
    public Client getClient(){ return client; }

    public void setName(String value){ name = value; }
    public void setIdOrder(int value){ idOrder = value; }
    public void setStatus(String value){ status = value; }
    public void setClient(Client cl){ client = cl; }
}
