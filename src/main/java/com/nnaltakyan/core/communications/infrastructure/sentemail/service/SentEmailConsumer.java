package com.nnaltakyan.core.communications.infrastructure.sentemail.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SentEmailConsumer
{

	@KafkaListener(topics = "sentemail", groupId = "communication-sentemail")
	public void consume(final String message)
	{
		System.out.println("Consumed message: " + message);
	}
}
