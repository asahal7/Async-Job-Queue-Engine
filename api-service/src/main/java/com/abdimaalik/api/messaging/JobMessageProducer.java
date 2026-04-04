package com.abdimaalik.api.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.abdimaalik.api.config.RabbitConfig;
import com.abdimaalik.api.dto.JobRequest;

@Service
public class JobMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public JobMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(JobRequest jobRequest) {
        rabbitTemplate.convertAndSend(RabbitConfig.JOB_QUEUE, jobRequest);
    }
}