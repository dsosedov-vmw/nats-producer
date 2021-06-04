package com.dsosedov.natsproducer.configuration;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NatsConfiguration {

    @Bean
    public Connection getNatsConnection() throws IOException, InterruptedException {
        return Nats.connect();
    }

}
