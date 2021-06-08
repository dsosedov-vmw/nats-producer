package com.dsosedov.natsproducer.configuration;

import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NatsConfiguration {

    @Bean
    public StreamingConnection getNatsConnection() throws IOException, InterruptedException {
        StreamingConnectionFactory cf = new StreamingConnectionFactory("test-cluster", "nats-producer");
        return cf.createConnection();
    }

}
