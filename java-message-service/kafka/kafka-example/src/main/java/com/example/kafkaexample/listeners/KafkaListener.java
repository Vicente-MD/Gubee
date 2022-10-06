package com.example.kafkaexample.listeners;

import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @org.springframework.kafka.annotation
            .KafkaListener(topics = "example", groupId = "groupId")
    public void listener(String message) {
        System.out.println("Message received from kafka: " + message);
    }
}
