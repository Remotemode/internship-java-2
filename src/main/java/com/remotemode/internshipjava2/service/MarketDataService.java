package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.model.Rate;
import com.remotemode.internshipjava2.model.RateInfo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class MarketDataService {

    private final MarketDataClient marketDataClient;
    private final RateCache rateCache;
    private final SimpMessagingTemplate template;

    public MarketDataService(MarketDataClient marketDataClient, RateCache rateCache, SimpMessagingTemplate template) {
        this.marketDataClient = marketDataClient;
        this.rateCache = rateCache;
        this.template = template;
    }

    public List<RateInfo> getRateInfo() {
        Map<String, Rate> rates = marketDataClient.getRates().getRates();

        for (Map.Entry<String, Rate> entry : rates.entrySet()) {
            rateCache.addOrUpdateRateCache(entry.getValue());
        }

        return new ArrayList<>(rateCache.getRateCacheInfo().values());
    }

    @Scheduled(fixedDelay = 5000)
    @SubscribeMapping("/topic/updateRates")
    private void getRateUpdates() {
        List<RateInfo> rateInfo = getRateInfo();
        this.template.convertAndSend("/topic/updateRates", rateInfo);
    }
}