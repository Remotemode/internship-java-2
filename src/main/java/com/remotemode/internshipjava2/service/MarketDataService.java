package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.model.Rate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MarketDataService {

    private final MarketDataClient marketDataClient;

    public MarketDataService(MarketDataClient marketDataClient) {
        this.marketDataClient = marketDataClient;
        getRatesFromExternalSource();
    }

    void getRatesFromExternalSource() {
        Map<String, Rate> rates = marketDataClient.getRates().getRates();
    }
}