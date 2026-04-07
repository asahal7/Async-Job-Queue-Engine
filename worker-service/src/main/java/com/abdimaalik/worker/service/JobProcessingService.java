package com.abdimaalik.worker.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.abdimaalik.worker.domain.Job;
import com.abdimaalik.worker.domain.JobStatus;
import com.abdimaalik.worker.dto.JobMessage;
import com.abdimaalik.worker.repository.JobRepository;

@Service
public class JobProcessingService {

    private final JobRepository jobRepository;

    public JobProcessingService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void process(JobMessage message) {
        UUID jobId = message.getJobId();

        System.out.println("Looking up job: " + jobId);

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found: " + jobId));

        try {
            job.setStatus(JobStatus.PROCESSING);
            job.setUpdatedAt(LocalDateTime.now());
            jobRepository.save(job);
            System.out.println("Marked PROCESSING: " + jobId);

            simulateWork(message);

            job.setStatus(JobStatus.COMPLETED);
            job.setErrorMessage(null);
            job.setUpdatedAt(LocalDateTime.now());
            jobRepository.save(job);
            System.out.println("Marked COMPLETED: " + jobId);

        } catch (Exception e) {
            job.setStatus(JobStatus.FAILED);
            job.setErrorMessage(e.getMessage());
            job.setUpdatedAt(LocalDateTime.now());
            jobRepository.save(job);
            System.out.println("Marked FAILED: " + jobId + " | reason: " + e.getMessage());
        }
    }

    private void simulateWork(JobMessage message) throws InterruptedException {
        Thread.sleep(3000);

        if ("FAIL".equalsIgnoreCase(message.getPayload())) {
            throw new RuntimeException("Simulated job failure");
        }
    }
}