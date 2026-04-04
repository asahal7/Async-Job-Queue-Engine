package com.abdimaalik.worker.listener;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener {

    @RabbitListener(queues = "job.queue")
    public void handleMessage(Map<String, Object> jobRequest) {
        System.out.println("Received job type: " + jobRequest.get("jobType"));
        System.out.println("Received payload: " + jobRequest.get("payload"));
    }
}