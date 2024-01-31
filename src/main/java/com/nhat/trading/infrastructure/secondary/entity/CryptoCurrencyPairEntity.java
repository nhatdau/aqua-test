package com.nhat.trading.infrastructure.secondary.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "crypto_currency_pair")
@Data
public class CryptoCurrencyPairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "source_currency")
    private String sourceCurrency;
    @Column(name = "target_currency")
    private String targetCurrency;
    @Column(name = "ask_price")
    private BigDecimal askPrice;
    @Column(name = "bid_price")
    private BigDecimal bidPrice;
}
