package com.nhat.trading.infrastructure.secondary;

import com.nhat.trading.infrastructure.secondary.entity.CryptoCurrencyPairEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoCurrencyPairRepository extends CrudRepository<CryptoCurrencyPairEntity, Integer> {
    CryptoCurrencyPairEntity findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);
}
