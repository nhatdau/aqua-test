package com.nhat.trading.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CryptoCurrency {
    USDT("USDT"), ETH("ETH"), BTC("BTC");
    private final String value;

    CryptoCurrency(String value) {
        this.value = value;
    }

    public static CryptoCurrency getByValue(String value) {
        return Arrays.stream(CryptoCurrency.values()).filter(cryptoCurrency -> cryptoCurrency.getValue().equals(value))
                .findFirst().orElse(null);
    }
}
