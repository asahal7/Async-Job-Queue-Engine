package com.abdimaalik.api.dto;

import java.util.UUID;

import com.abdimaalik.api.domain.JobStatus;

public class SubmitJobResponse {

    private UUID jobId;
    private JobStatus status;

    public SubmitJobResponse(UUID jobId, JobStatus status) {
        this.jobId = jobId;
        this.status = status;
    }

    public UUID getJobId() {
        return jobId;
    }

    public JobStatus getStatus() {
        return status;
    }
}