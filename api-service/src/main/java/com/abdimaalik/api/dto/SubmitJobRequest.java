package com.abdimaalik.api.dto;

public class SubmitJobRequest {

    private String type;
    private String payload;

    public SubmitJobRequest() {
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}