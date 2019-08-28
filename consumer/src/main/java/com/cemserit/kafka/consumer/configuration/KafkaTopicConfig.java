package com.cemserit.kafka.consumer.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cemserit on 27.08.2019.
 */
@Configuration
public class KafkaTopicConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${kafka.topic.message.topicName}")
    private String messageTopicName;
    @Value(value = "${kafka.topic.message.partition}")
    private int messagePartition;
    @Value(value = "${kafka.topic.message.replication}")
    private short messageReplication;
    @Value(value = "${kafka.topic.log.topicName}")
    private String logTopicName;
    @Value(value = "${kafka.topic.log.partition}")
    private int logPartition;
    @Value(value = "${kafka.topic.log.replication}")
    private short logReplication;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic eventTopic() {
        return new NewTopic(messageTopicName, messagePartition, messageReplication);
    }

    @Bean
    public NewTopic logTopic() {
        return new NewTopic(logTopicName, logPartition, logReplication);
    }
}
