package com.dsosedov.natsproducer.controllers;

import com.dsosedov.natsproducer.messages.Message;
import com.dsosedov.natsproducer.models.FooRequest;
import io.nats.streaming.StreamingConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
@RequestMapping("api/v1/foo")
public class FooController {

    private final StreamingConnection sc;

    public FooController(StreamingConnection sc) {
        this.sc = sc;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody FooRequest request) throws IOException, InterruptedException, TimeoutException {
        log.debug("Publishing the following message: " + request.getMessage());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Message.newBuilder().setText(request.getMessage()).build().writeTo(stream);

        sc.publish("dataIn", stream.toByteArray());

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
