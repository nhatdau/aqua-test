package com.nhat.trading.infrastructure.primary.model;

import com.nhat.trading.domain.CryptoCurrencyPair;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CurrencyPairPrice {
    private String symbol;
    private BigDecimal askPrice;
    private BigDecimal bidPrice;

    public static CurrencyPairPrice from(CryptoCurrencyPair cryptoCurrencyPair) {
        CurrencyPairPrice currencyPairPrice = new CurrencyPairPrice();
        currencyPairPrice.setSymbol(cryptoCurrencyPair.getSourceCurrency().getValue().concat(cryptoCurrencyPair.getTargetCurrency().getValue()));
        currencyPairPrice.setBidPrice(cryptoCurrencyPair.getBidPrice());
        currencyPairPrice.setAskPrice(cryptoCurrencyPair.getAskPrice());
        return currencyPairPrice;
    }
}
