package com.abdimaalik.api.messaging;

import java.util.UUID;

public class JobMessage {

    private UUID jobId;
    private String type;
    private String payload;

    public JobMessage() {
    }

    public JobMessage(UUID jobId, String type, String payload) {
        this.jobId = jobId;
        this.type = type;
        this.payload = payload;
    }

    public UUID getJobId() {
        return jobId;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public void setJobId(UUID jobId) {
        this.jobId = jobId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}