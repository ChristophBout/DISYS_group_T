package org.example.service;

import org.example.communication.Producer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class ProducerService {
    private final String queueName;
    private final String brokerUrl;
    private final String id;
    private static final Random random = new Random();

    public ProducerService(String inDestination, String outDestination, String brokerUrl) {
        this.queueName = outDestination;
        this.brokerUrl = brokerUrl;
        this.id = UUID.randomUUID().toString();
        System.out.println("Community Energy Producer (" + this.id + ") started...");
    }

    public void sendEnergyReading() {
        try {
            double kwh = generatePlausibleKwh();
            String datetime = LocalDateTime.now().toString();

            Map<String, Object> message = new HashMap<>();
            message.put("type", "PRODUCER");
            message.put("association", "COMMUNITY");
            message.put("kwh", Math.round(kwh * 1000.0) / 1000.0);
            message.put("datetime", datetime);

            String json = toJson(message);
            Producer.send(json, queueName, brokerUrl);

            System.out.println("Sent: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double generatePlausibleKwh() {
        int hour = LocalDateTime.now().getHour();
        double sunlightFactor = Math.cos((hour - 12) * Math.PI / 24);
        sunlightFactor = Math.max(0.1, sunlightFactor);
        return 0.001 + 0.004 * sunlightFactor + (random.nextDouble() * 0.001);
    }

    private String toJson(Map<String, Object> data) {
        return String.format("{\"type\":\"%s\",\"association\":\"%s\",\"kwh\":%.3f,\"datetime\":\"%s\"}",
                data.get("type"), data.get("association"), data.get("kwh"), data.get("datetime"));
    }
}