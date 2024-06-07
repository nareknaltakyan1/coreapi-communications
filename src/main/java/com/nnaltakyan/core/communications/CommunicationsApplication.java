package com.nnaltakyan.core.communications;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CommunicationsApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CommunicationsApplication.class, args);
	}

	@PostConstruct
	public void afterStart()
	{
		log.debug("Communications Application started");
	}
}
