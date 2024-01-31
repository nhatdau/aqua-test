package com.nhat.trading.infrastructure.primary.scheduler;

import com.nhat.trading.application.CryptoCurrencyApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateCryptoCurrencyPriceScheduler {
    private static final Logger logger = LoggerFactory.getLogger(UpdateCryptoCurrencyPriceScheduler.class);
    private final CryptoCurrencyApplicationService cryptoCurrencyApplicationService;

    public UpdateCryptoCurrencyPriceScheduler(CryptoCurrencyApplicationService cryptoCurrencyApplicationService) {
        this.cryptoCurrencyApplicationService = cryptoCurrencyApplicationService;
    }

    @Scheduled(fixedRateString = "${price.update_interval}")
    public void updateCryptoCurrencyPrices() {
        logger.info("[UpdateCryptoCurrencyPriceScheduler] updateCryptoCurrencyPrices");
        cryptoCurrencyApplicationService.updateCryptoCurrencyPrices();
    }
}
