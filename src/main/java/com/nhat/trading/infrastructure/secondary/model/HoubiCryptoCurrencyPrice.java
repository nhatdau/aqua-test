package com.nhat.trading.infrastructure.secondary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HoubiCryptoCurrencyPrice {
    private String symbol;
    private BigDecimal ask;
    private BigDecimal bid;
}
