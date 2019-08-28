package com.cemserit.kafka.consumer.configuration;

import com.cemserit.kafka.core.model.Log;
import com.cemserit.kafka.core.model.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cemserit on 27.08.2019.
 */
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${kafka.topic.message.groupName}")
    private String messageGroupName;
    @Value(value = "${kafka.topic.log.groupName}")
    private String logGroupName;
    @Value(value = "${kafka.topic.message.partition}")
    private int messagePartition;
    @Value(value = "${kafka.topic.log.partition}")
    private int logPartition;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Log> logKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Log> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(logConsumerFactory());
        factory.setConcurrency(logPartition);
        factory.getContainerProperties().setPollTimeout(1500);

        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> messageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(messageConsumerFactory());
        factory.setConcurrency(messagePartition);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    private Map<String, Object> messageConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, messageGroupName);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    private Map<String, Object> logConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, logGroupName);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    private ConsumerFactory<String, Message> messageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(messageConsumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(Message.class));
    }

    private ConsumerFactory<String, Log> logConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(logConsumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(Log.class));
    }
}