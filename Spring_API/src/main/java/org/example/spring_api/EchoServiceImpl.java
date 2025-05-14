package org.example.spring_api;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EchoServiceImpl {

    private final RabbitTemplate rabbit;

    public EchoServiceImpl(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @RabbitListener(queues = "MyInQueue")
    public void processMessage(String message) {
        System.out.println("Empfangen: " + message);
        try {
            // Simuliere lang andauernde Verarbeitung
            Thread.sleep(message.length() * 100L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String response = "Echo: " + message;
        System.out.println("Antwort: " + response);
        rabbit.convertAndSend("MyOutQueue", response);
    }
}
