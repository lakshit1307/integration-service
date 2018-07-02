package com.healthedge.integrationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaSerializer<T> implements Serializer<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSerializer.class);

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            LOGGER.error("Error while creating object serializer", e);
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}
