package com.abdimaalik.worker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdimaalik.worker.domain.Job;

public interface JobRepository extends JpaRepository<Job, UUID> {
}