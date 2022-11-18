package com.example.lab8;

import com.Core.Kitchen.Chief;

public class ChiefDTO {
    public String name;
    public String pizzaName;
    public String toDo;
    public String status;
    public int id;
    public Chief chief;

    public ChiefDTO(){}

    public ChiefDTO(String Name, String PizzaName, String ToDo, String Status, int Id, Chief chief){
        name = Name;
        pizzaName = PizzaName;
        toDo = ToDo;
        status = Status;
        id = Id;
        this.chief = chief;
    }

    public void setName(String Name){ name = Name; }
    public void setStatus(String Status){ status = Status; }
    public void setChief(Chief chief){ this.chief = chief; }
    public void setPizzaName(String NamePizza){ pizzaName = NamePizza; }
    public void setToDo(String ToDo){ toDo = ToDo; }
    public void setId(int Id){ id = Id; }

    public String getName(){ return name; }
    public String getStatus(){ return status; }
    public String getPizzaName(){ return pizzaName; }
    public String getToDo(){ return toDo; }
    public Chief getChief(){ return  chief; }
    public int getId(){ return id; }
}
