package com.dsosedov.natsproducer.controllers;

import com.dsosedov.natsproducer.messages.Message;
import com.dsosedov.natsproducer.models.FooRequest;
import io.nats.client.Connection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/foo")
public class FooController {

    private Logger logger = Logger.getLogger(FooController.class.getName());

    private Connection natsConnection;

    public FooController(Connection natsConnection) {
        this.natsConnection = natsConnection;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody FooRequest request) throws IOException {
        logger.info("Publishing the following message: " + request.getMessage());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Message.newBuilder().setText(request.getMessage()).build().writeTo(stream);
        natsConnection.publish("dataIn", stream.toByteArray());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
