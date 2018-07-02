package com.healthedge.integrationservice.kafka;

import com.healthedge.integrationservice.service.TenantService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private Long tenantId;

    private TenantService tenantService;

    public KafkaConsumer(Long tenantId, String bootstrapServers, TenantService tenantService) {
        this.tenantId = tenantId;
        this.bootstrapServers = bootstrapServers;
        this.tenantService = tenantService;
    }

    private String bootstrapServers;


    //    @KafkaListener(topics = "test")
    public void listen(Object message) {
        LOGGER.info("Received Messasge in group foo: " + message);
    }

    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "some");
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Object.class));
    }

    public void consumeDataFromTenant() {
        String tenantTopic = getTenantTopic(tenantId);
        ContainerProperties containerProps = new ContainerProperties(tenantTopic);
        containerProps.setMessageListener(new KafkaMessageListener<String, Object>(tenantService));
        KafkaMessageListenerContainer<String, Object> container = new KafkaMessageListenerContainer<>(consumerFactory(), containerProps);
        container.setBeanName(tenantId + "_consumer");
        container.start();
    }

    private String getTenantTopic(Long tenantId) {
        return "test";
    }


}
