package com.nhat.trading.domain;

import java.util.Optional;

public interface CryptoCurrencyPairRepository {
    void storeCryptoCurrencyPair(CryptoCurrencyPair cryptoCurrencyPair);
    void storeOrUpdateCryptoCurrencyPair(CryptoCurrencyPair cryptoCurrencyPair);
    Optional<CryptoCurrencyPair> retrieveCryptoCurrencyPair(CryptoCurrency sourceCurrency, CryptoCurrency targetCurrency);
}
