package com.base.a;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private float price;
    private float weight;
    private List<String> ingredients;

    private Pizza() {

    }

    private Pizza setName(String name) {
        this.name = name;
        return this;
    }
    private Pizza setWeight(float weight) {
        this.weight = weight;
        return this;
    }
    private Pizza setPrice(float price) {
        this.price = price;
        return this;
    }
    private Pizza setComponents(List<String> components) {
        this.ingredients = components;
        return this;
    }
    private Pizza addIngredient(String component) {
        this.ingredients.add(component);
        return this;
    }

    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public float getWeight() {
        return weight;
    }
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    @Override
    public String toString() {
        return getName();
    }
    public static class JsonPizzaParser {
        public static Pizza parse(JSONObject pizzaJson) {
            var parsedPizza = new Pizza();

            parsedPizza.setName(pizzaJson.get("name").toString())
                    .setWeight((float) (double)pizzaJson.get("weight"))
                    .setPrice((float) (double)pizzaJson.get("price"))
                    .setComponents((List<String>) pizzaJson.get("components"));

            return parsedPizza;
        }
    }
}
