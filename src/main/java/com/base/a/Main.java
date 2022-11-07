package com.base.a;

import com.base.a.API.ApplicationController;

public class Main {
    public static void main(String[] args) {
        var app = ApplicationController.getInstance();
        app.init();
        app.run();
    }
}