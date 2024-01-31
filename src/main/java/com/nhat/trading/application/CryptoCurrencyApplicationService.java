package com.nhat.trading.application;

import com.nhat.trading.domain.CryptoCurrencyPair;
import com.nhat.trading.domain.CryptoCurrencyPairService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@DependsOn({"restCryptoCurrencyPairPriceRetriever","jpaCryptoCurrencyPairRepositoryImpl"})
public class CryptoCurrencyApplicationService {
    private final CryptoCurrencyPairService cryptoCurrencyPairService;

    public CryptoCurrencyApplicationService(CryptoCurrencyPairService cryptoCurrencyPairService) {
        this.cryptoCurrencyPairService = cryptoCurrencyPairService;
    }

    @Transactional
    public void updateCryptoCurrencyPrices() {
        cryptoCurrencyPairService.updatePriceOfCurrencyPairs();
    }

    public CryptoCurrencyPair getBestPrice(String source, String target) {
        return cryptoCurrencyPairService.getBestPrice(source, target);
    }
}
