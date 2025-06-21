package org.example;

import org.example.service.EnergyProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class EnergyProducerMain {
    private static final Logger logger = LoggerFactory.getLogger(EnergyProducerMain.class);
    private static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        EnergyProducerService producer = new EnergyProducerService("unused", "energy_queue", "localhost");
        logger.info("EnergyProducer started");

        while (true) {
            producer.sendEnergyReading();
            // Random delay between 1 and 5 seconds
            int delay = 1000 + random.nextInt(4000); // 0–3999 + 1000
            Thread.sleep(delay);
        }
    }
}