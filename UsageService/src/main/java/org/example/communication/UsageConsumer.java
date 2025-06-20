package org.example.communication;
import com.rabbitmq.client.*;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class UsageConsumer {

    public static void receive(String queueName, long timeout, String brokerUrl, DeliverCallback deliverCallback) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(brokerUrl);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // channel.queueDeclare(queueName, true, false, false, null);

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            System.out.println("Cancelled: " + consumerTag);
        });

        System.out.println("Subscribed to queue: " + queueName);
    }
}