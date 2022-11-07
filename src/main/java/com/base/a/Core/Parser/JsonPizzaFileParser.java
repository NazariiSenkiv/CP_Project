package com.base.a.Parser;

import com.base.a.Kitchen.Pizza;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonPizzaFileParser {
    public static List<Pizza> parseFile(String filePath) {
        List<Pizza> pizzas = new ArrayList<>();

        var parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONArray pizzasJsonArray = (JSONArray) parser.parse(reader);

            for(Object pizza : pizzasJsonArray) {
                JSONObject pizzaJsonObject = (JSONObject) pizza;

                pizzas.add(Pizza.JsonPizzaParser.parse(pizzaJsonObject));
            }
        } catch (Exception e) {
            System.out.println("File parse error in class HouseholdApplianceJsonParser, " + e);
        }

        return pizzas;
    }
}
