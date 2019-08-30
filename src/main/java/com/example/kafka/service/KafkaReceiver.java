package com.example.kafka.service;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

	private CountDownLatch greetingLatch = new CountDownLatch(1);
	private CountDownLatch customerLatch = new CountDownLatch(1);

	public CountDownLatch customerLatch() {
		return customerLatch;
	}

	public CountDownLatch greetingLatch() {
		return greetingLatch;
	}

	@KafkaListener(topicPartitions = @TopicPartition(topic = "${partitioned.topic.name}", partitions = "0" ),containerFactory = "customerKafkaListenerContainerFactory")
    public void customer(ConsumerRecord<?, ?> customerRecord, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOGGER.info("Received Customer Message: " + customerRecord + " from partition: " + partition);
        this.customerLatch.countDown();
    }

	@KafkaListener(topicPartitions = @TopicPartition(topic = "${partitioned.topic.name}", partitions = "1" ), containerFactory = "greetingKafkaListenerContainerFactory")
	public void greeting(ConsumerRecord<?, ?> greetingRecord, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOGGER.info("Received Greeting Message: " + greetingRecord + " from partition: " + partition);
        this.greetingLatch.countDown();
	}

}
