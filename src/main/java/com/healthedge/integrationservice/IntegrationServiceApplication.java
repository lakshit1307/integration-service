package com.healthedge.integrationservice;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@ComponentScan(basePackages = "com.healthedge.*")
@EntityScan(basePackages = "com.healthedge.integrationservice.entity")
@EnableJpaRepositories("com.healthedge.integrationservice.repository")
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
public class IntegrationServiceApplication {

    private static SpringCamelContext context = new SpringCamelContext();

    public static void main(String[] args) {
        SpringApplication.run(IntegrationServiceApplication.class, args);

    }

}
