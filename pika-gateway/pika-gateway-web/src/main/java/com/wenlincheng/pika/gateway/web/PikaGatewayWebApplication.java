package com.wenlincheng.pika.gateway.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

@SpringBootApplication
@RemoteApplicationEventScan
public class PikaGatewayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikaGatewayWebApplication.class, args);
    }

}
