package com.example.lab8;

public class InfoOrderDTO {
    public String namePizza;
    public Integer numberOfPizza;

    public InfoOrderDTO(){}

    public InfoOrderDTO(String name, int num){
        namePizza = name;
        numberOfPizza = num;
    }

    public void setNamePizza(String name){ namePizza = name; }
    public void setNumberOfPizza(int num){ numberOfPizza = num; }

    public String getNamePizza(){ return namePizza; }
    public Integer getNumberOfPizza(){ return numberOfPizza; }
}
