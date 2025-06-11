package org.example;

import org.example.service.EnergyProducerService;

public class CurrentPercentageMain {
    public static void main(String[] args) throws Exception {
        EnergyProducerService producer = new EnergyProducerService("unused", "energy_queue", "localhost");

        while (true) {
            producer.sendEnergyReading();  // we call a direct send method
            Thread.sleep(5000);
        }
    }
}
