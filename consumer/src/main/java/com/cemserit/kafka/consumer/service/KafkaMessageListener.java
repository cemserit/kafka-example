package com.cemserit.kafka.consumer.service;

import com.cemserit.kafka.core.model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

/**
 * Created by cemserit on 27.08.2019.
 */
@Service
public class KafkaMessageListener extends KafkaListenerEndpointRegistry {

    private Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "${kafka.topic.message.topicName}", groupId = "${kafka.topic.message.groupName}",
            containerFactory = "messageKafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, Message> message) {
        logger.info("{}", message);
    }
}
