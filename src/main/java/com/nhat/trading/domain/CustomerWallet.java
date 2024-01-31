package com.nhat.trading.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerWallet {
    private String userName;
    private List<CurrencyBalance> balanceList;
}
