package com.abdimaalik.api.dto;

public class JobRequest {

    private String jobType;
    private String payload;

    public JobRequest() {
    }

    public JobRequest(String jobType, String payload) {
        this.jobType = jobType;
        this.payload = payload;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}