package com.abdimaalik.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdimaalik.api.messaging.JobMessageProducer;

@RestController
public class JobController {

    private final JobMessageProducer jobMessageProducer;

    public JobController(JobMessageProducer jobMessageProducer) {
        this.jobMessageProducer = jobMessageProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam(defaultValue = "hello from api-service") String message) {
        jobMessageProducer.send(message);
        return "Message sent: " + message;
    }
}