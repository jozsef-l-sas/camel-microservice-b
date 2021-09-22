package com.jozsef.camel.processors;

import com.jozsef.camel.domain.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CurrencyExchangeProcessor {

    public void processMessage(CurrencyExchange currencyExchange) {
        log.info("Doing some processing with currency exchange conversion: {}", currencyExchange.getConversionMultiple());
    }

}
