package com.abdimaalik.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdimaalik.api.domain.Job;

public interface JobRepository extends JpaRepository<Job, UUID> {
}