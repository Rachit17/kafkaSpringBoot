package com.example.kafka.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * This scheduler service runs at a fixed interval and checks for any
 * new event happening like customer or greeting Events. 
 * Also publishes the event/message to Kafka Queue.
 */

@Component
public class SchedulerService {

	@Autowired
	private EventService eventService;

	@Scheduled(fixedRateString = "${scheduler.customerEventFrequency}")
	public void processCustomerEvent() {
		eventService.createCustomers();

	}

	@Scheduled(fixedRateString = "${scheduler.greetingEventFrequency}")
	public void processGreetingEvent() {
		eventService.createGreetings();

	}

}