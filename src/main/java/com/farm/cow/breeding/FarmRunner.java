package com.farm.cow.breeding;

import com.farm.cow.breeding.controller.FarmController;

public class FarmRunner {
    FarmController farmController = new FarmController();

    void run() {
        farmController.start();
    }

    public static void main(String[] args) {
        new FarmRunner().run();
    }
}
