package org.example;

import org.example.service.EnergyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EnergyUserMain {
    public static void main(String[] args) throws Exception {
        EnergyUserService user = new EnergyUserService("unused", "energy_queue", "localhost");
        logger.info("EnergyUser started");

        while (true) {
            user.sendEnergyUsage();  // Send every few seconds
            Thread.sleep(5000);
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(EnergyUserMain.class);

}