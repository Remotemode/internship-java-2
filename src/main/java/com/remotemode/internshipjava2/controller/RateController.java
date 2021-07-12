package com.remotemode.internshipjava2.controller;

import com.remotemode.internshipjava2.model.RateInfo;
import com.remotemode.internshipjava2.service.MarketDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RateController {

    private final MarketDataService marketDataService;

    public RateController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping("/initial")
    public List<RateInfo> getInitialRateInfo() {
        return marketDataService.getRateInfo();
    }
}