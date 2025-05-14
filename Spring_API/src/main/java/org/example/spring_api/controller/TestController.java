package org.example.spring_api.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        rabbitTemplate.convertAndSend("MyInQueue", message);
        return ResponseEntity.ok("Nachricht gesendet: " + message);
    }
}
