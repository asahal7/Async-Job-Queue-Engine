package com.abdimaalik.worker.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener {

    @RabbitListener(queues = "job.queue")
    public void handleMessage(String message) {
        System.out.println("Received message: " + message);
    }
}