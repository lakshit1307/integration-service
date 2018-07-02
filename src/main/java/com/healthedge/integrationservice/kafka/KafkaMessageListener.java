package com.healthedge.integrationservice.kafka;

import com.healthedge.integrationservice.service.TenantService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class KafkaMessageListener<K, V> implements MessageListener<K, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageListener.class);

    private TenantService tenantService;

    public KafkaMessageListener(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @Override
    public void onMessage(ConsumerRecord<K, V> data) {

        LOGGER.info("received: " + data);
    }
}
