package com.example.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.kafka.model.CustomerEvent;
import com.example.kafka.model.GreetingEvent;

@Configuration
public class SenderConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public ProducerFactory<String, CustomerEvent> customerProducerFactory() {
		Map<String, Object> customerProps = new HashMap<>();
		customerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		customerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		customerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(customerProps);
	}

	@Bean
	public KafkaTemplate<String, CustomerEvent> customerKafkaTemplate() {
		return new KafkaTemplate<>(customerProducerFactory());
	}

	@Bean
	public ProducerFactory<String, GreetingEvent> greetingProducerFactory() {
		Map<String, Object> greetingProps = new HashMap<>();
		greetingProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		greetingProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		greetingProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(greetingProps);
	}

	@Bean
	public KafkaTemplate<String, GreetingEvent> greetingKafkaTemplate() {
		return new KafkaTemplate<>(greetingProducerFactory());
	}

}
