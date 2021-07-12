package com.remotemode.internshipjava2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExchangeRates {
    Map<String, Rate> rates;
}