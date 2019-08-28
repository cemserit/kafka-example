package com.cemserit.kafka.producer.service;

import com.cemserit.kafka.core.model.Log;
import com.cemserit.kafka.core.model.Message;

/**
 * Created by cemserit on 26.08.2019.
 */
public interface ProducerService {
    void sendMessage(Message event);

    void sendLog(Log log);
}
