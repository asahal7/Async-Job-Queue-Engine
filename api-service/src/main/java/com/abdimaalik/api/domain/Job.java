package com.abdimaalik.api.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String errorMessage;

    protected Job() {
    }

    public Job(UUID id, String type, String payload, JobStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.payload = payload;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public void markProcessing() {
        this.status = JobStatus.PROCESSING;
        this.updatedAt = LocalDateTime.now();
    }

    public void markCompleted() {
        this.status = JobStatus.COMPLETED;
        this.updatedAt = LocalDateTime.now();
        this.errorMessage = null;
    }

    public void markFailed(String errorMessage) {
        this.status = JobStatus.FAILED;
        this.updatedAt = LocalDateTime.now();
        this.errorMessage = errorMessage;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public JobStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}