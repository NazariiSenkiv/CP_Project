package com.Core.Parser;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonNameFileParser {
    public static List<String> parse(String filepath) {
        var parser = new JSONParser();
        
        var list = new ArrayList<String>();
        try (var reader = new FileReader(filepath)) {
            JSONArray array = (JSONArray) parser.parse(reader);

            for (var name : array) {
                list.add((String)name);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File %s is not found!", filepath);
        } catch (IOException e) {
            System.out.println("Something went wrong with reading data");
        } catch (ParseException e) {
            System.out.println("Something went wrong with parsing json");
        }

        return list;
    }
}
