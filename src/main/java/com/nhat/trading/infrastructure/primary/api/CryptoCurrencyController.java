package com.nhat.trading.infrastructure.primary.api;

import com.nhat.trading.application.CryptoCurrencyApplicationService;
import com.nhat.trading.infrastructure.primary.model.CurrencyPairPrice;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoCurrencyController {
    private final CryptoCurrencyApplicationService cryptoCurrencyApplicationService;

    public CryptoCurrencyController(CryptoCurrencyApplicationService cryptoCurrencyApplicationService) {
        this.cryptoCurrencyApplicationService = cryptoCurrencyApplicationService;
    }

    @GetMapping("/currency-pair")
    CurrencyPairPrice getBestPrice(@Param("source") String source, @Param("target") String target) {
        return CurrencyPairPrice.from(cryptoCurrencyApplicationService.getBestPrice(source, target));
    }
}
