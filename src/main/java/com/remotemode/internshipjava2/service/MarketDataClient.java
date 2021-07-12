package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.model.ExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://api.coingecko.com/api/v3", value = "marketDataClient")
public interface MarketDataClient {

    @GetMapping("/exchange_rates")
    ExchangeRates getRates();
}