package com.example.lab8;

import java.util.ArrayList;
import java.util.List;

public class Resources {
    private static Resources instance;
    private List<java.net.URL> resources;
    private Resources(){ resources = new ArrayList<>(); }

    public static Resources getInstance(){
        if(instance == null){
            instance = new Resources();
        }
        return instance;
    }

    public void addResource(java.net.URL url){
        resources.add(url);
    }

    public java.net.URL getResource(int index){
        return resources.get(index);
    }
}
