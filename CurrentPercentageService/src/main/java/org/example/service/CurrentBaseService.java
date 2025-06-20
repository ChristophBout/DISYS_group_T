package org.example.service;

import com.rabbitmq.client.DeliverCallback;
import org.example.communication.CurrentPercentageConsumer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public abstract class CurrentBaseService {
    protected final String inDestination;
    protected final String outDestination;
    protected final String brokerUrl;

    public CurrentBaseService(String inDestination, String outDestination, String brokerUrl) {
        this.inDestination = inDestination;
        this.outDestination = outDestination;
        this.brokerUrl = brokerUrl;
    }

    public void run() throws IOException, TimeoutException {
        System.out.println("Listening to queue: " + inDestination);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String input = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(">>> Nachricht empfangen im DeliverCallback: " + input);
            String output = executeInternal(input);
            // Optional: logge das Ergebnis
            System.out.println(">>> Ergebnis executeInternal: " + output);
        };

        CurrentPercentageConsumer.receive(inDestination, 10000, brokerUrl, deliverCallback);
        System.out.println("Subscribed to queue: " + inDestination);
    }

    protected abstract String executeInternal(String input);
}