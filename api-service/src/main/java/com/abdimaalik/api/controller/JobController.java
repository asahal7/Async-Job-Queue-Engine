package com.abdimaalik.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdimaalik.api.dto.JobRequest;
import com.abdimaalik.api.messaging.JobMessageProducer;

@RestController
public class JobController {

    private final JobMessageProducer jobMessageProducer;

    public JobController(JobMessageProducer jobMessageProducer) {
        this.jobMessageProducer = jobMessageProducer;
    }

    @GetMapping("/send")
    public String sendMessage(
            @RequestParam(defaultValue = "EMAIL") String jobType,
            @RequestParam(defaultValue = "welcome-user-123") String payload
    ) {
        JobRequest jobRequest = new JobRequest(jobType, payload);
        jobMessageProducer.send(jobRequest);
        return "Job sent: type=" + jobType + ", payload=" + payload;
    }
}