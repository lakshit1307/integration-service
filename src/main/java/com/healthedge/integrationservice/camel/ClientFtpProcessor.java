package com.healthedge.integrationservice.camel;

import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import com.healthedge.integrationservice.dto.MemberTenantScore;
import com.healthedge.integrationservice.kafka.KafkaProducer;
import com.healthedge.integrationservice.service.EncoderDecoder;
import com.healthedge.integrationservice.service.TenantService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

public class ClientFtpProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    private KafkaProducer kafkaProducer;

    private Long tenantId;

    private TenantService tenantService;

    @Autowired
    private EncoderDecoder encoderDecoder;

    public ClientFtpProcessor(KafkaProducer kafkaProducer, Long tenantId, TenantService tenantService) {
        this.kafkaProducer = kafkaProducer;
        this.tenantId = tenantId;
        this.tenantService = tenantService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String lineAsString = exchange.getIn().getBody(String.class);
        kafkaProducer.sendMessage(tenantService.getTenantTopic(tenantId), readLine(lineAsString, tenantId));
    }

    private MemberTenantScore readLine(String clientDataAsString, Long tenantId) {
        String[] clientDatas = clientDataAsString.split(",");
        try {
            String member = encoderDecoder.decrypt(clientDatas[0], "TENANT_" + tenantId.toString());
        } catch (Exception e) {
            LOGGER.error("Error while decrypting member id: " + clientDataAsString + " for tenantId: " + tenantId, e);
        }
        Long memberId = Long.parseLong(clientDatas[0]);
        Double riskScore = Double.parseDouble(StringUtils.trimAllWhitespace(clientDatas[1]));
        return memberTenantScore(tenantId, memberId, riskScore);
    }

    private MemberTenantScore memberTenantScore(Long tenantId, Long memberId, Double riskScore) {
        MemberTenantScore memberTenantScore = new MemberTenantScore();
        memberTenantScore.setMemberId(memberId);
        memberTenantScore.setRiskScore(riskScore);
        memberTenantScore.setTenantId(tenantId);
        return memberTenantScore;
    }
}
