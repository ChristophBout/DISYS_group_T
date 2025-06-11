package org.example;

import org.example.service.EnergyUserService;

public class EnergyUserMain {
    public static void main(String[] args) throws Exception {
        EnergyUserService user = new EnergyUserService("unused", "energy_queue", "localhost");

        while (true) {
            user.sendEnergyUsage();  // Send every few seconds
            Thread.sleep(5000);
        }
    }
}