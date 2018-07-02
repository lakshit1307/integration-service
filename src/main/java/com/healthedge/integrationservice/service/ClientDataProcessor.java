package com.healthedge.integrationservice.service;

import com.healthedge.integrationservice.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientDataProcessor {


    @Autowired
    private ReaderService readerService;

    @Autowired
    private KafkaProducer kafkaProducer;

//    private void sendDataToKafkaPipeLine(Long tenantId){
//        //TODO Add the correct Path for Tenant
//        String path="";
//        List<Map<String, String>> csvFilePathData=readerService.readFileFromPath(path);
//        SomeEntity se=createDataForPersistence(csvFilePathData);
//        kafkaProducer.sendMessage("test", se);
//    }
//
//    SomeEntity createDataForPersistence(List<Map<String, String>> csvFilePathData){
//        return new SomeEntity();
//    }


}
