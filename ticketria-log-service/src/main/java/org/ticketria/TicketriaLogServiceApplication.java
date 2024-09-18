package org.ticketria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;

@EnableDiscoveryClient
@SpringBootApplication
@EnableKafka
public class TicketriaLogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketriaLogServiceApplication.class, args);
    }

}
