package com.Core.App;

public class AppConfig {
    private static volatile AppConfig instance;
    public static String jsonPizzasPath = "pizzas.json";
    public static String jsonNamesPath = "names.json";
    public static int paydesksCount = 4;
    public static int chiefsCount = 4;
    public static int orderTimeInSeconds = 16;
    public static int clientSpawnDelaySeconds = 10;
    public static int itemCookingMinTime = 2;
    public static int numberOfPizzas = 4;

    public static ChiefWorkMode chiefWorkMode = ChiefWorkMode.CONVEYOR;

    public static OrderGenerationStrategy orderGenerationStrategy = OrderGenerationStrategy.RANDOM_ORDER;

    private AppConfig() {

    }

    public static AppConfig getInstance() {
        AppConfig result = instance;

        if (result != null) {
            return result;
        }

        synchronized (AppConfig.class) {
            if (instance == null) {
                instance = new AppConfig();
            }
            return instance;
        }
    }
}
