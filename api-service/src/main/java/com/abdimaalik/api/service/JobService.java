package com.abdimaalik.api.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.abdimaalik.api.config.RabbitConfig;
import com.abdimaalik.api.domain.Job;
import com.abdimaalik.api.domain.JobStatus;
import com.abdimaalik.api.dto.SubmitJobRequest;
import com.abdimaalik.api.messaging.JobMessage;
import com.abdimaalik.api.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final RabbitTemplate rabbitTemplate;

    public JobService(JobRepository jobRepository, RabbitTemplate rabbitTemplate) {
        this.jobRepository = jobRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Job submitJob(SubmitJobRequest request) {
        UUID jobId = UUID.randomUUID();

        Job job = new Job(
                jobId,
                request.getType(),
                request.getPayload(),
                JobStatus.PENDING,
                LocalDateTime.now()
        );

        jobRepository.save(job);

        JobMessage message = new JobMessage(jobId, request.getType(), request.getPayload());
        rabbitTemplate.convertAndSend(RabbitConfig.JOB_QUEUE, message);

        return job;
    }

    public Job getJob(UUID jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found: " + jobId));
    }
}