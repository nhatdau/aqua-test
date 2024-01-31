package com.nhat.trading.domain;

import java.util.LinkedList;
import java.util.List;

public class CryptoCurrencyPairService {
    private final CryptoCurrencyPairRepository cryptoCurrencyPairRepository;
    private final CryptoCurrencyPairPriceRetriever cryptoCurrencyPairPriceRetriever;

    public CryptoCurrencyPairService(CryptoCurrencyPairRepository cryptoCurrencyPairRepository, CryptoCurrencyPairPriceRetriever cryptoCurrencyPairPriceRetriever) {
        this.cryptoCurrencyPairRepository = cryptoCurrencyPairRepository;
        this.cryptoCurrencyPairPriceRetriever = cryptoCurrencyPairPriceRetriever;
    }

    public void updatePriceOfCurrencyPairs() {
        List<CryptoCurrencyPair> cryptoCurrencyPairs1 = new LinkedList<>();
        cryptoCurrencyPairs1.add(CryptoCurrencyPair.builder().sourceCurrency(CryptoCurrency.BTC).targetCurrency(CryptoCurrency.USDT).build());
        cryptoCurrencyPairs1.add(CryptoCurrencyPair.builder().sourceCurrency(CryptoCurrency.ETH).targetCurrency(CryptoCurrency.USDT).build());
        cryptoCurrencyPairPriceRetriever.retrieveFromBinance(cryptoCurrencyPairs1);
        List<CryptoCurrencyPair> cryptoCurrencyPairs2 = new LinkedList<>();
        cryptoCurrencyPairs2.add(CryptoCurrencyPair.builder().sourceCurrency(CryptoCurrency.BTC).targetCurrency(CryptoCurrency.USDT).build());
        cryptoCurrencyPairs2.add(CryptoCurrencyPair.builder().sourceCurrency(CryptoCurrency.ETH).targetCurrency(CryptoCurrency.USDT).build());
        cryptoCurrencyPairPriceRetriever.retrieveFromHoubi(cryptoCurrencyPairs2);
        CryptoCurrencyPair cryptoCurrencyPair = CryptoCurrencyPair.builder().sourceCurrency(CryptoCurrency.BTC).targetCurrency(CryptoCurrency.USDT).build();
        cryptoCurrencyPair.setAskPrice(cryptoCurrencyPairs1.get(0).getAskPrice().compareTo(cryptoCurrencyPairs2.get(0).getAskPrice()) < 0 ? cryptoCurrencyPairs1.get(0).getAskPrice() : cryptoCurrencyPairs2.get(0).getAskPrice());
        cryptoCurrencyPair.setBidPrice(cryptoCurrencyPairs1.get(0).getBidPrice().compareTo(cryptoCurrencyPairs2.get(0).getAskPrice()) > 0 ? cryptoCurrencyPairs1.get(0).getBidPrice() : cryptoCurrencyPairs2.get(0).getBidPrice());
        cryptoCurrencyPairRepository.storeOrUpdateCryptoCurrencyPair(cryptoCurrencyPair);
        cryptoCurrencyPair = CryptoCurrencyPair.builder().sourceCurrency(CryptoCurrency.ETH).targetCurrency(CryptoCurrency.USDT).build();
        cryptoCurrencyPair.setAskPrice(cryptoCurrencyPairs1.get(1).getAskPrice().compareTo(cryptoCurrencyPairs2.get(1).getAskPrice()) < 0 ? cryptoCurrencyPairs1.get(1).getAskPrice() : cryptoCurrencyPairs2.get(1).getAskPrice());
        cryptoCurrencyPair.setBidPrice(cryptoCurrencyPairs1.get(1).getBidPrice().compareTo(cryptoCurrencyPairs2.get(1).getAskPrice()) > 0 ? cryptoCurrencyPairs1.get(1).getBidPrice() : cryptoCurrencyPairs2.get(1).getBidPrice());
        cryptoCurrencyPairRepository.storeOrUpdateCryptoCurrencyPair(cryptoCurrencyPair);
    }

    public CryptoCurrencyPair getBestPrice(String source, String target) {
        CryptoCurrency sourceCurrency = CryptoCurrency.getByValue(source);
        CryptoCurrency targetCurrency = CryptoCurrency.getByValue(target);
        return cryptoCurrencyPairRepository.retrieveCryptoCurrencyPair(sourceCurrency, targetCurrency).orElse(null);
    }
}
