package com.nhat.trading.infrastructure.secondary;

import com.nhat.trading.domain.CryptoCurrency;
import com.nhat.trading.domain.CryptoCurrencyPair;
import com.nhat.trading.infrastructure.secondary.entity.CryptoCurrencyPairEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("jpaCryptoCurrencyPairRepositoryImpl")
public class JpaCryptoCurrencyPairRepositoryImpl implements com.nhat.trading.domain.CryptoCurrencyPairRepository {
    private static final Logger logger = LoggerFactory.getLogger(JpaCryptoCurrencyPairRepositoryImpl.class);
    private final CryptoCurrencyPairRepository cryptoCurrencyPairRepository;

    public JpaCryptoCurrencyPairRepositoryImpl(CryptoCurrencyPairRepository cryptoCurrencyPairRepository) {
        this.cryptoCurrencyPairRepository = cryptoCurrencyPairRepository;
    }

    @Override
    public void storeCryptoCurrencyPair(CryptoCurrencyPair cryptoCurrencyPair) {
        if (cryptoCurrencyPair == null) {
            throw new IllegalArgumentException("Argument cryptoCurrencyPair is null");
        }
        CryptoCurrencyPairEntity cryptoCurrencyPairEntity = new CryptoCurrencyPairEntity();
        cryptoCurrencyPairEntity.setSourceCurrency(cryptoCurrencyPair.getSourceCurrency().getValue());
        cryptoCurrencyPairEntity.setTargetCurrency(cryptoCurrencyPair.getTargetCurrency().getValue());
        cryptoCurrencyPairEntity.setAskPrice(cryptoCurrencyPair.getAskPrice());
        cryptoCurrencyPairEntity.setBidPrice(cryptoCurrencyPair.getBidPrice());
        cryptoCurrencyPairRepository.save(cryptoCurrencyPairEntity);
    }

    @Override
    public void storeOrUpdateCryptoCurrencyPair(CryptoCurrencyPair cryptoCurrencyPair) {
        if (cryptoCurrencyPair == null) {
            throw new IllegalArgumentException("Argument cryptoCurrencyPair is null");
        }
        CryptoCurrencyPairEntity cryptoCurrencyPairEntity = cryptoCurrencyPairRepository.findBySourceCurrencyAndTargetCurrency(cryptoCurrencyPair.getSourceCurrency().getValue(), cryptoCurrencyPair.getTargetCurrency().getValue());
        if (cryptoCurrencyPairEntity == null) {
            cryptoCurrencyPairEntity = new CryptoCurrencyPairEntity();
            cryptoCurrencyPairEntity.setSourceCurrency(cryptoCurrencyPair.getSourceCurrency().getValue());
            cryptoCurrencyPairEntity.setTargetCurrency(cryptoCurrencyPair.getTargetCurrency().getValue());
        }
        cryptoCurrencyPairEntity.setAskPrice(cryptoCurrencyPair.getAskPrice());
        cryptoCurrencyPairEntity.setBidPrice(cryptoCurrencyPair.getBidPrice());
        cryptoCurrencyPairRepository.save(cryptoCurrencyPairEntity);
    }

    @Override
    public Optional<CryptoCurrencyPair> retrieveCryptoCurrencyPair(CryptoCurrency sourceCurrency, CryptoCurrency targetCurrency) {
        CryptoCurrencyPairEntity cryptoCurrencyPairEntity = cryptoCurrencyPairRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency.getValue(), targetCurrency.getValue());
        if (cryptoCurrencyPairEntity == null) {
            return Optional.empty();
        }
        return Optional.of(CryptoCurrencyPair.builder().sourceCurrency(sourceCurrency).targetCurrency(targetCurrency)
                .askPrice(cryptoCurrencyPairEntity.getAskPrice()).bidPrice(cryptoCurrencyPairEntity.getBidPrice()).build());
    }
}
