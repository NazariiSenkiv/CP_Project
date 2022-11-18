package com.example.lab8;

import com.API.ApplicationController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TimerTask;

public class UpdateListChiefsTimer extends TimerTask {
    private Page3Controller page3Controller;
    public UpdateListChiefsTimer(Page3Controller page3Controller) {
        super();
        this.page3Controller = page3Controller;
    }
    @Override
    public void run() {
        List<ChiefDTO> chiefs = new ArrayList<ChiefDTO>();
        ApplicationController applicationController;
        applicationController = ApplicationController.getInstance();
        var data = applicationController.getData();

            chiefs = data.getChiefInfoList().stream()
                    .map(e -> {
                        return new ChiefDTO(e.getName(),
                                ((e.getCurrentTask() == null) ? "":e.getCurrentTask().getPizzaName()),
                                ((e.getCurrentTask() == null) ? "нема роботи":e.getCurrentTask().getName()),
                                e.getStatus(), e.getId() + 1, e);
                    }).toList();
            //chiefs.sort(Comparator.comparing(ChiefDTO::getId));
            this.page3Controller.setChiefsForTable(chiefs);


    }
}
