package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.model.Rate;
import com.remotemode.internshipjava2.model.RateInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MarketDataService {

    private final MarketDataClient marketDataClient;
    private final RateCache rateCache;

    public MarketDataService(MarketDataClient marketDataClient, RateCache rateCache) {
        this.marketDataClient = marketDataClient;
        this.rateCache = rateCache;
    }

    public List<RateInfo> getRateInfo() {
        Map<String, Rate> rates = marketDataClient.getRates().getRates();

        for (Map.Entry<String, Rate> entry : rates.entrySet()) {
            rateCache.addOrUpdateRateCache(entry.getValue());
        }

        return new ArrayList<>(rateCache.getRateCacheInfo().values());
    }
}