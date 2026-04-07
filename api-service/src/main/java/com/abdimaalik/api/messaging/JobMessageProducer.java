package com.abdimaalik.api.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.abdimaalik.api.config.RabbitConfig;

@Component
public class JobMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public JobMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(JobMessage jobMessage) {
        rabbitTemplate.convertAndSend(RabbitConfig.JOB_QUEUE, jobMessage);
    }
}