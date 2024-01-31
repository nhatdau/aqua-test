package com.nhat.trading.domain;

import java.util.List;

public interface CryptoCurrencyPairPriceRetriever {
    void retrieveFromBinance(List<CryptoCurrencyPair> cryptoCurrencyPairs);
    void retrieveFromHoubi(List<CryptoCurrencyPair> cryptoCurrencyPairs);
}
