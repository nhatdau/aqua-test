package com.nhat.config;

import com.nhat.trading.domain.CryptoCurrencyPairPriceRetriever;
import com.nhat.trading.domain.CryptoCurrencyPairRepository;
import com.nhat.trading.domain.CryptoCurrencyPairService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class BeanConfiguration {

    @Bean
////    @DependsOn({"restCryptoCurrencyPairPriceRetriever","jpaCryptoCurrencyPairRepositoryImpl"})
    public CryptoCurrencyPairService cryptoCurrencyPairService(CryptoCurrencyPairRepository cryptoCurrencyPairRepository, CryptoCurrencyPairPriceRetriever cryptoCurrencyPairPriceRetriever) {
        return new CryptoCurrencyPairService(cryptoCurrencyPairRepository, cryptoCurrencyPairPriceRetriever);
    }
}
