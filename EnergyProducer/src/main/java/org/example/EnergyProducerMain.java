package org.example;

import org.example.service.ProducerService;

public class EnergyProducerMain {
    public static void main(String[] args) throws Exception {
        ProducerService producer = new ProducerService("unused", "energy_queue", "localhost");

        while (true) {
            producer.sendEnergyReading();  // we call a direct send method
            Thread.sleep(5000);
        }
    }
}