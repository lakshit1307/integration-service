package com.healthedge.integrationservice.camel;

import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import com.healthedge.integrationservice.dto.MemberTenantScore;
import com.healthedge.integrationservice.kafka.KafkaProducer;
import com.healthedge.integrationservice.service.TenantService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

public class ClientFtpProcessor implements Processor {

    private KafkaProducer kafkaProducer;

    private Long tenantId;

    private TenantService tenantService;

    public ClientFtpProcessor(KafkaProducer kafkaProducer, Long tenantId, TenantService tenantService) {
        this.kafkaProducer = kafkaProducer;
        this.tenantId = tenantId;
        this.tenantService = tenantService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String lineAsString = exchange.getIn().getBody(String.class);
        kafkaProducer.sendMessage(tenantService.getTenantTopic(tenantId), readLine(lineAsString));
    }

    private MemberTenantScore readLine(String clientDataAsString) {
        String[] clientDatas = clientDataAsString.split(",");
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
