package com.nhat.trading.infrastructure.secondary;

import com.nhat.trading.domain.CryptoCurrencyPair;
import com.nhat.trading.domain.CryptoCurrencyPairPriceRetriever;
import com.nhat.trading.infrastructure.secondary.model.BinanceCryptoCurrencyPrice;
import com.nhat.trading.infrastructure.secondary.model.HoubiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("restCryptoCurrencyPairPriceRetriever")
public class RestCryptoCurrencyPairPriceRetriever implements CryptoCurrencyPairPriceRetriever {

    @Value("${price.url.binance}")
    private String binanceUrl;
    @Value("${price.url.houbi}")
    private String houbiUrl;
    private final RestTemplate restTemplate;

    public RestCryptoCurrencyPairPriceRetriever() {
        restTemplate = new RestTemplate();
    }

    @Override
    public void retrieveFromBinance(List<CryptoCurrencyPair> cryptoCurrencyPairs) {
        List<BinanceCryptoCurrencyPrice> cryptoCurrencyPriceList = restTemplate.exchange(binanceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<BinanceCryptoCurrencyPrice>>() {}).getBody();
        cryptoCurrencyPairs.forEach(cryptoCurrencyPair -> cryptoCurrencyPriceList.stream().filter(binanceCryptoCurrencyPrice ->
                        binanceCryptoCurrencyPrice.getSymbol().equalsIgnoreCase(cryptoCurrencyPair.getSourceCurrency().getValue().concat(cryptoCurrencyPair.getTargetCurrency().getValue())))
                .findFirst().ifPresent(binanceCryptoCurrencyPrice -> {
                    cryptoCurrencyPair.setAskPrice(binanceCryptoCurrencyPrice.getAskPrice());
                    cryptoCurrencyPair.setBidPrice(binanceCryptoCurrencyPrice.getBidPrice());
                }));
    }

    @Override
    public void retrieveFromHoubi(List<CryptoCurrencyPair> cryptoCurrencyPairs) {
        HoubiResponse houbiResponse = restTemplate.getForObject(houbiUrl, HoubiResponse.class);
        cryptoCurrencyPairs.forEach(cryptoCurrencyPair -> houbiResponse.getData().stream().filter(houbiCryptoCurrencyPrice ->
                        houbiCryptoCurrencyPrice.getSymbol().equalsIgnoreCase(cryptoCurrencyPair.getSourceCurrency().getValue().concat(cryptoCurrencyPair.getTargetCurrency().getValue())))
                .findFirst().ifPresent(houbiCryptoCurrencyPrice -> {
                    cryptoCurrencyPair.setAskPrice(houbiCryptoCurrencyPrice.getAsk());
                    cryptoCurrencyPair.setBidPrice(houbiCryptoCurrencyPrice.getBid());
                }));
    }
}
