package com.nhat.trading.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CryptoCurrencyPair {
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
    private CryptoCurrency sourceCurrency;
    private CryptoCurrency targetCurrency;
}
