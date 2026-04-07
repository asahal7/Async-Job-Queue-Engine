package com.abdimaalik.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abdimaalik.api.domain.Job;
import com.abdimaalik.api.dto.SubmitJobRequest;
import com.abdimaalik.api.dto.SubmitJobResponse;
import com.abdimaalik.api.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SubmitJobResponse submitJob(@RequestBody SubmitJobRequest request) {
        Job job = jobService.submitJob(request);
        return new SubmitJobResponse(job.getId(), job.getStatus());
    }

    @GetMapping("/{jobId}")
    public Job getJob(@PathVariable UUID jobId) {
        return jobService.getJob(jobId);
    }
}