package com.healthedge.integrationservice.camel;

import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import com.healthedge.integrationservice.kafka.KafkaProducer;
import com.healthedge.integrationservice.service.TenantService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;

import java.util.Date;

public class ClientFtpRoute extends RouteBuilder {

    private Long tenantId;

    private KafkaProducer kafkaProducer;

    private TenantService tenantService;

    public ClientFtpRoute(Long tenantId, KafkaProducer kafkaProducer, TenantService tenantService) {
        this.tenantId = tenantId;
        this.kafkaProducer = kafkaProducer;
        this.tenantService = tenantService;
    }

    @Override
    public void configure() throws Exception {
        String directoryPath = IntegrationServiceConstants.OUTPUT_DIR_PATH;
        String fileName = getFileName();
        from("file://" + directoryPath + "?fileName=" + fileName + "&noop=true").routeId(IntegrationServiceConstants.CLIENT_ROUTE + tenantId.toString() + new Date().getTime())
                .split(body().tokenize("\n")).streaming()
                .process(new ClientFtpProcessor(kafkaProducer, tenantId, tenantService));


//        String topicName = tenantService.getTenantTopic(tenantId);
//        String kafkaServer = "kafka:localhost:9092";
//        String zooKeeperHost = "zookeeperHost=localhost&zookeeperPort=2181";
//        String serializerClass = "serializerClass=kafka.serializer.StringEncoder";
//
//        String toKafka = new StringBuilder().append(kafkaServer).append("?").append(topicName).append("&")
//                .append(zooKeeperHost).append("&").append(serializerClass).toString();

//        KafkaComponent kafka = new KafkaComponent();
//        kafka.setBroke("{{kafka.host}}:{{kafka.port}}");
//        camelContext.addComponent("kafka", kafka);

//
//        from("file://" + directoryPath + "?fileName=" + fileName + "&noop=true").marshal().string("UTF-8")
//                .split(body().tokenize("\n")).streaming().to(toKafka);
    }

    private String getFileName() {
        return "OUTPUT_" + tenantId.toString() + ".csv";
    }
}
