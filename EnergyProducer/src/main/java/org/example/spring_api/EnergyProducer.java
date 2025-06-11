package org.example.spring_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class EnergyProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Random random = new Random();
    private static final String QUEUE_NAME = "energy_queue";

    public EnergyProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendEnergyMessage() {
        try {
            double kwh = 0.001 + (0.004 * 1);

            Map<String, Object> message = new HashMap<>();
            message.put("type", "PRODUCER");
            message.put("association", "COMMUNITY");
            message.put("kwh", Math.round(kwh * 1000.0) / 1000.0);
            message.put("datetime", LocalDateTime.now().toString());

            String jsonMessage = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(QUEUE_NAME, jsonMessage);

            System.out.println("Sent: " + jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}