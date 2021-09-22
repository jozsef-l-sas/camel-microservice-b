package com.jozsef.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:my-kafka-topic")
                .to("log:received-message-from-kafka");
    }

}
