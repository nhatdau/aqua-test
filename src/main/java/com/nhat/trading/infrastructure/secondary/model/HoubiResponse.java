package com.nhat.trading.infrastructure.secondary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class HoubiResponse {
    private List<HoubiCryptoCurrencyPrice> data;
}
