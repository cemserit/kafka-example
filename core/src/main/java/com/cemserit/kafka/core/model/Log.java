package com.cemserit.kafka.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Created by cemserit on 27.08.2019.
 */
public class Log {
    private UUID key;
    private String rawLog;
    private long timestamp;

    @JsonCreator
    public Log(@JsonProperty("key") UUID key,
               @JsonProperty("rawLog") String rawLog,
               @JsonProperty("timestamp") long timestamp) {
        this.key = key;
        this.rawLog = rawLog;
        this.timestamp = timestamp;
    }

    public UUID getKey() {
        return key;
    }

    public String getRawLog() {
        return rawLog;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Log{" +
                "key=" + key +
                ", rawLog='" + rawLog + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
