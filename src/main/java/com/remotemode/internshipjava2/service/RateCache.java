package com.remotemode.internshipjava2.service;

import com.remotemode.internshipjava2.model.Rate;
import com.remotemode.internshipjava2.model.RateInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RateCache {

    private final Map<String, RateInfo> rateCache = new HashMap<>();

    public void addOrUpdateRateCache(Rate rate) {
        if (!rateCache.containsKey(rate.getName())) {
            addRateToCache(rate);
        } else {
            updateRateCache(rate);
        }
    }

    private void addRateToCache(Rate rate) {
        RateInfo rateInfo = new RateInfo(rate.getName(), rate.getUnit(), rate.getValue());
        rateCache.put(rate.getName(), rateInfo);
    }

    private void updateRateCache(Rate rate) {
        RateInfo rateInfo = rateCache.get(rate.getName());
        rateInfo.setLastPrice(rate.getValue());
    }

    public Map<String, RateInfo> getRateCacheInfo() {
        return rateCache;
    }
}