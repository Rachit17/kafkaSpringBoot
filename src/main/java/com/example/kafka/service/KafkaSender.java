package com.example.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafka.model.CustomerEvent;
import com.example.kafka.model.GreetingEvent;

@Service
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, CustomerEvent> customerKafkaTemplate;

	@Autowired
	private KafkaTemplate<String, GreetingEvent> greetingKafkaTemplate;

	@Value(value = "${partitioned.topic.name}")
	private String partitionTopicName;

	public void sendMessageToCustomerPartition(CustomerEvent customerEvent, int partition) {
		customerKafkaTemplate.send(partitionTopicName, partition, null, customerEvent);
	}
	
	public void sendMessageToGreetingPartition(GreetingEvent greetingEvent, int partition) {
		greetingKafkaTemplate.send(partitionTopicName, partition, null, greetingEvent);
	}

}
