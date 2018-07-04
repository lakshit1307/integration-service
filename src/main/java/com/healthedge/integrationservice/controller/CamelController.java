package com.healthedge.integrationservice.controller;

import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import com.healthedge.integrationservice.dto.BaseResponse;
import com.healthedge.integrationservice.kafka.KafkaConsumer;
import com.healthedge.integrationservice.kafka.KafkaProducer;
import com.healthedge.integrationservice.service.EncoderDecoder;
import com.healthedge.integrationservice.service.ProducerServiceImpl;
import com.healthedge.integrationservice.service.TenantService;
import org.apache.camel.spring.SpringCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class CamelController {

//    private FtpRoute timer=new FtpRoute();

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelController.class);

    @Autowired
    private ProducerServiceImpl producerService;

    @Autowired
    private SpringCamelContext context;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private EncoderDecoder encoderDecoder;


    @GetMapping("/trigger/{tenantId}")
    public ResponseEntity<BaseResponse> triggerTenantReuest(@PathVariable(value = "tenantId") Long tenantId) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setStatus(producerService.processDataForTenantId(tenantId));
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error in triggering load", e);
            baseResponse.setStatus(IntegrationServiceConstants.FAILURE);
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sendkafka/{message}")
    public ResponseEntity<BaseResponse> kafkaSendMessage(@PathVariable(value = "message") String message) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            kafkaProducer.sendMessage("test1", message);
            baseResponse.setStatus(IntegrationServiceConstants.SUCCESS);
            KafkaConsumer kafkaConsumer = new KafkaConsumer(1L, bootstrapServers, tenantService);
            kafkaConsumer.consumeDataFromTenant();
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error while sending kafka message", e);
            baseResponse.setStatus(IntegrationServiceConstants.FAILURE);
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tenantresulttokafka/{tenantId}")
    public ResponseEntity<BaseResponse> sendTenantDataToKafka(@PathVariable(value = "tenantId") Long tenantId) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            producerService.sendTenantDataToKafka(tenantId);
            KafkaConsumer kafkaConsumer = new KafkaConsumer(1L, bootstrapServers, tenantService);
            kafkaConsumer.consumeDataFromTenant();
            baseResponse.setStatus(IntegrationServiceConstants.SUCCESS);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error while sending kafka message", e);
            baseResponse.setStatus(IntegrationServiceConstants.FAILURE);
            baseResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/encrypt/{tenantId}")
    public ResponseEntity<BaseResponse> encrypt(@PathVariable(value = "tenantId") Long tenantId) {
            BaseResponse baseResponse = new BaseResponse();
        try {
            String encoded=encoderDecoder.encrypt(tenantId.toString(),"tenant1Key");
            String decoded=encoderDecoder.decrypt(encoded,"tenant1Key");
            baseResponse.setStatus(IntegrationServiceConstants.SUCCESS);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        } catch (Exception e) {

            baseResponse.setStatus(IntegrationServiceConstants.FAILURE);
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

//    @GetMapping(path = "/")
//    private String getAll(){
//        try {
//            context.addRoutes(timer);
//            context.startRoute("TIMER_ROUTE");
//            Thread.sleep (60*1*1000);
//            context.stopRoute("TIMER_ROUTE");
//            return "SUCCESS";
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return "FAILURE";
//        }
//    }
}
