package org.example.service;

import org.example.communication.EnergyUser;

import java.time.LocalDateTime;
import java.util.*;

public class EnergyUserService {
    private final String queueName;
    private final String brokerUrl;
    private final String id;
    private static final Random random = new Random();

    public EnergyUserService(String inDestination, String outDestination, String brokerUrl) {
        this.queueName = outDestination;
        this.brokerUrl = brokerUrl;
        this.id = UUID.randomUUID().toString();
        System.out.println("Community Energy User (" + this.id + ") started...");
    }

    public void sendEnergyUsage() {
        try {
            double kwh = generatePlausibleKwh();
            String datetime = LocalDateTime.now().toString();

            String message = String.format(Locale.US, "USER,COMMUNITY,%.3f,%s", kwh, datetime);            EnergyUser.send(message, queueName, brokerUrl);

            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double generatePlausibleKwh() {
        int hour = LocalDateTime.now().getHour();
        double demandFactor;

        if ((hour >= 7 && hour <= 10) || (hour >= 17 && hour <= 21)) {
            // Peak hours
            demandFactor = 1.0;
        } else if (hour >= 0 && hour <= 5) {
            // Nighttime
            demandFactor = 0.2;
        } else {
            // Moderate demand
            demandFactor = 0.5;
        }

        return 0.0005 + 0.0025 * demandFactor + (random.nextDouble() * 0.0005);
    }

    private String toJson(Map<String, Object> data) {
        return String.format("{\"type\":\"%s\",\"association\":\"%s\",\"kwh\":%.3f,\"datetime\":\"%s\"}",
                data.get("type"), data.get("association"), data.get("kwh"), data.get("datetime"));
    }
}