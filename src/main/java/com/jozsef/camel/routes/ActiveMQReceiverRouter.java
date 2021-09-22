package com.jozsef.camel.routes;

import com.jozsef.camel.domain.CurrencyExchange;
import com.jozsef.camel.processors.CurrencyExchangeProcessor;
import com.jozsef.camel.transformers.CurrencyExchangeTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder {

    private final CurrencyExchangeProcessor currencyExchangeProcessor;
    private final CurrencyExchangeTransformer currencyExchangeTransformer;

    public ActiveMQReceiverRouter(CurrencyExchangeProcessor currencyExchangeProcessor, CurrencyExchangeTransformer currencyExchangeTransformer) {
        this.currencyExchangeProcessor = currencyExchangeProcessor;
        this.currencyExchangeTransformer = currencyExchangeTransformer;
    }

    @Override
    public void configure() throws Exception {
        configureForSplitQueue();
    }

    private void configureForSplitQueue() {
        from("activemq:split-queue")
                .to("log:received-message-from-activemq");
    }

    private void configureForXML() {
        from("activemq:my-activemq-xml-queue")
                .unmarshal().jacksonxml(CurrencyExchange.class)
                .bean(currencyExchangeProcessor)
                .bean(currencyExchangeTransformer)
                .to("log:received-message-from-activemq");
    }

    private void configureForJSON() {
        from("activemq:my-activemq-json-queue")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .bean(currencyExchangeProcessor)
                .bean(currencyExchangeTransformer)
                .to("log:received-message-from-activemq");
    }

}
