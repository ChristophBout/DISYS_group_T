package org.example.communication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EnergyProducer {
    public static void send(String text, String queueName, String brokerUrl) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(brokerUrl);

        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            // Queue dauerhaft machen
            //channel.queueDeclare(queueName, true, false, false, null);

            channel.basicPublish("", queueName, null, text.getBytes());
            //System.out.println("Sent: " + text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}