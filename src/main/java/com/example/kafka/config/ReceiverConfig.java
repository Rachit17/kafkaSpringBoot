package com.example.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.kafka.model.CustomerEvent;
import com.example.kafka.model.GreetingEvent;

@EnableKafka
@Configuration
public class ReceiverConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${spring.kafka.consumer.group-id}")
	private String consumerGroupId;

	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String autoOffsetReset;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CustomerEvent> customerKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CustomerEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(customerConsumerFactory());
		return factory;
	}

	public ConsumerFactory<String, CustomerEvent> customerConsumerFactory() {
		Map<String, Object> customerProps = new HashMap<>();
		customerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		customerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroupId");
		customerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

		return new DefaultKafkaConsumerFactory<>(customerProps, new StringDeserializer(),
				new JsonDeserializer<>(CustomerEvent.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, GreetingEvent> greetingKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, GreetingEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(greetingConsumerFactory());
		return factory;
	}

	public ConsumerFactory<String, GreetingEvent> greetingConsumerFactory() {
		Map<String, Object> greetingProps = new HashMap<>();
		greetingProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		greetingProps.put(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroupId");
		greetingProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

		return new DefaultKafkaConsumerFactory<>(greetingProps, new StringDeserializer(),
				new JsonDeserializer<>(GreetingEvent.class));
	}

}
