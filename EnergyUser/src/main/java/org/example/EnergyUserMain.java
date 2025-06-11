package org.example;

import org.example.service.UserService;

public class EnergyUserMain {
    public static void main(String[] args) throws Exception {
        UserService user = new UserService("unused", "energy_queue", "localhost");

        while (true) {
            user.sendEnergyUsage();  // Send every few seconds
            Thread.sleep(5000);
        }
    }
}