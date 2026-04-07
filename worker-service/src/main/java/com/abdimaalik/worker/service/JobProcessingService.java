package com.abdimaalik.worker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abdimaalik.worker.domain.Job;
import com.abdimaalik.worker.dto.JobMessage;
import com.abdimaalik.worker.repository.JobRepository;

@Service
public class JobProcessingService {

    private final JobRepository jobRepository;

    public JobProcessingService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Transactional
    public void process(JobMessage message) {
        System.out.println("Looking up job: " + message.getJobId());

        Job job = jobRepository.findById(message.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found: " + message.getJobId()));

        try {
            job.markProcessing();
            jobRepository.save(job);
            System.out.println("Marked PROCESSING: " + job.getId());

            Thread.sleep(2000);

            job.markCompleted();
            jobRepository.save(job);
            System.out.println("Marked COMPLETED: " + job.getId());

        } catch (Exception e) {
            job.markFailed(e.getMessage());
            jobRepository.save(job);
            System.out.println("Marked FAILED: " + job.getId());
            throw new RuntimeException("Job processing failed", e);
        }
    }
}