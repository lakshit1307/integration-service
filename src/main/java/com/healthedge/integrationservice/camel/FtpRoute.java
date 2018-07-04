package com.healthedge.integrationservice.camel;

import com.healthedge.integrationservice.common.IntegrationServiceConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.OnCompletionProcessor;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class FtpRoute extends RouteBuilder {

    private String routeName;

    private String fileName;

    private String tenantFtpUrl;

    public FtpRoute(String fileName, String tenantFtpUrl, String routeName) {
        this.fileName = fileName;
        this.tenantFtpUrl = tenantFtpUrl;
        this.routeName = routeName;
    }

    @Override
    public void configure() throws Exception {

        String directoryPath = IntegrationServiceConstants.TEMP_DIR_PATH;
        from("file://" + directoryPath + "?fileName=" + fileName + "&noop=true")
                .routeId(routeName)
                .to(tenantFtpUrl);
    }
}