package org.example.spring_api;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue inQueue() {
        return new Queue("MyInQueue", false);
    }

    @Bean
    public Queue outQueue() {
        return new Queue("MyOutQueue", false);
    }
}
