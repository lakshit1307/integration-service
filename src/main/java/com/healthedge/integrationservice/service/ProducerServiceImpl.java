package com.healthedge.integrationservice.service;

import com.healthedge.integrationservice.camel.ClientFtpRoute;
import com.healthedge.integrationservice.camel.FtpRoute;
import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import com.healthedge.integrationservice.dao.TenantAttributeDao;
import com.healthedge.integrationservice.kafka.KafkaProducer;
import org.apache.camel.spring.SpringCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProducerServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ClientTransformationService clientTransformationService;

    @Autowired
    private TenantAttributeDao tenantAttributeDao;

    @Autowired
    private SpringCamelContext context;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private TenantService tenantService;

    public String processDataForTenantId(Long tenantId) throws Exception {
        List<Map<String, String>> clientDataSet = clientTransformationService.getTransformedDataSet(tenantId);
        Map<String, String> primaryAttributes = tenantAttributeDao.getTenantAttributesForAttributeType(tenantId, IntegrationServiceConstants.ATTRIBUTE_TYPE_PRIMARY);
        String consumptionProtocol = primaryAttributes.get(IntegrationServiceConstants.ATTRIBUTE_NAME_CONSUMPTION_PROTOCOL);
        if (consumptionProtocol.equalsIgnoreCase(IntegrationServiceConstants.CONSUMPTION_PROTOCOL_FTP)) {
            String fileName = clientTransformationService.transformToCsv(clientDataSet, tenantId);
            String ftpUrl = primaryAttributes.get(IntegrationServiceConstants.ATTRIBUTE_NAME_FTP_URL);
            Long currentTime = new Long(new Date().getTime());
            String routeName = "FTP_ROUTE" + tenantId.toString() + "_" + currentTime.toString();
            FtpRoute ftpRoute = new FtpRoute(fileName, ftpUrl, routeName);
            try {
                context.addRoutes(ftpRoute);
                context.getRouteStatus(routeName);
                return IntegrationServiceConstants.SUCCESS;
            } catch (Exception e) {
                LOGGER.error("Error while pricesssing Data for tenant Id " + tenantId, e);
                throw e;
            }
        }
        return IntegrationServiceConstants.FAILURE;
    }

    public void sendTenantDataToKafka(Long tenantId) throws Exception {
        LOGGER.debug("Sending tenant Data to kafka");
        ClientFtpRoute ftpRoute = new ClientFtpRoute(tenantId, kafkaProducer, tenantService);
        String routeId=IntegrationServiceConstants.CLIENT_ROUTE+tenantId.toString();
        if (context.getRoute(routeId)==null) {
            context.addRoutes(ftpRoute);
        }
        else {
            context.getRouteStatus(routeId);
            context.removeRoute(routeId);
        }
    }
}
