package com.abdimaalik.worker.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.abdimaalik.worker.config.RabbitConfig;
import com.abdimaalik.worker.dto.JobMessage;
import com.abdimaalik.worker.service.JobProcessingService;

@Component
public class JobListener {

    private final JobProcessingService jobProcessingService;

    public JobListener(JobProcessingService jobProcessingService) {
        this.jobProcessingService = jobProcessingService;
    }

    @RabbitListener(queues = RabbitConfig.JOB_QUEUE)
    public void handleMessage(JobMessage message) {
        System.out.println("Worker received job: " + message.getJobId());
        jobProcessingService.process(message);
    }
}