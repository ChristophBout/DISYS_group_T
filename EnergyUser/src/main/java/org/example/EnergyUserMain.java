package org.example;

import org.example.service.EnergyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class EnergyUserMain {
    private static final Logger logger = LoggerFactory.getLogger(EnergyUserMain.class);
    private static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        EnergyUserService user = new EnergyUserService("unused", "energy_queue", "localhost");
        logger.info("EnergyUser started");

        while (true) {
            user.sendEnergyUsage();
            // Random delay between 1 and 5 seconds
            int delay = 1000 + random.nextInt(4000); // 0–3999 + 1000
            Thread.sleep(delay);
        }
    }
}