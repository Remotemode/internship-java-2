package com.remotemode.internshipjava2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RateInfo {
    private final String symbolName;
    private final String symbolUnit;
    private final long startPrice;
    @Setter
    private long lastPrice;

    public RateInfo(String symbolName, String symbolUnit, long value) {
        this.symbolName = symbolName;
        this.symbolUnit = symbolUnit;
        this.startPrice = value;
    }
}