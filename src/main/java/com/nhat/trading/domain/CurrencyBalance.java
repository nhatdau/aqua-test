package com.nhat.trading.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyBalance {
    private CryptoCurrency currency;
    private BigDecimal balance;
}
