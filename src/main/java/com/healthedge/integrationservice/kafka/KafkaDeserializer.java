package com.healthedge.integrationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaDeserializer<T> implements Deserializer<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDeserializer.class);

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Object user = null;
        try {
            user = mapper.readValue(data, Object.class);
        } catch (Exception e) {
            LOGGER.error("Error while creating object deserializer", e);
        }
        return user;
    }

    @Override
    public void close() {

    }
}
