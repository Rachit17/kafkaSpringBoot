package com.example.kafka.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kafka.model.CustomerEvent;
import com.example.kafka.model.EventType;
import com.example.kafka.model.GreetingEvent;

@Component
public class EventService {

	private static final Logger log = LoggerFactory.getLogger(EventService.class);

	@Autowired
	KafkaSender kafkaSender;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private final AtomicLong counterCustomer = new AtomicLong();
	private final AtomicLong counterGreeting = new AtomicLong();

	public void createCustomers() {

		log.info("Customer Event Generation Kafka ");

		kafkaSender.sendMessageToCustomerPartition((new CustomerEvent(counterCustomer.incrementAndGet(), "Kafka Customer",
				dateFormat.format(new Date()), EventType.CUSTOMEREVENT.eventType())), 0);
	}

	public void createGreetings() {

		log.info("Greeting Event Generation Kafka ");

		kafkaSender.sendMessageToGreetingPartition(
				(new GreetingEvent(counterGreeting.incrementAndGet(), "Kafka Greeting",
						dateFormat.format(new Date()), EventType.GREETINGEVENT.eventType())),
				1);
	}

}
