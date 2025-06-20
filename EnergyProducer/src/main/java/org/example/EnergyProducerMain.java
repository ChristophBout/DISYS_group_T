package org.example;

import org.example.service.EnergyProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EnergyProducerMain {
    public static void main(String[] args) throws Exception {
        EnergyProducerService producer = new EnergyProducerService("unused", "energy_queue", "localhost");
        logger.info("Main started");

        while (true) {
            producer.sendEnergyReading();
            Thread.sleep(5000);
        }


    }
    private static final Logger logger = LoggerFactory.getLogger(EnergyProducerMain.class);
}