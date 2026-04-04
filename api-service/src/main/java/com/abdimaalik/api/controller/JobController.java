package com.abdimaalik.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abdimaalik.api.dto.JobRequest;
import com.abdimaalik.api.messaging.JobMessageProducer;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobMessageProducer jobMessageProducer;

    public JobController(JobMessageProducer jobMessageProducer) {
        this.jobMessageProducer = jobMessageProducer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String submitJob(@RequestBody JobRequest jobRequest) {
        jobMessageProducer.send(jobRequest);
        return "Job accepted: type=" + jobRequest.getJobType() +
                ", payload=" + jobRequest.getPayload();
    }
}