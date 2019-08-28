package com.cemserit.kafka.consumer.service;

import com.cemserit.kafka.core.model.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by cemserit on 27.08.2019.
 */
@Service
public class KafkaLogListener {
    private Logger logger = LoggerFactory.getLogger(KafkaLogListener.class);

    @KafkaListener(topics = "${kafka.topic.log.topicName}",
            groupId = "${kafka.topic.log.groupName}", containerFactory = "logKafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, Log> log) {
        logger.info("{}", log);
    }
}
