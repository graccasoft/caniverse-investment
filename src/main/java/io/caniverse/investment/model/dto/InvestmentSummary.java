package io.caniverse.investment.model.dto;


import java.math.BigDecimal;


public interface InvestmentSummary {
    BigDecimal getTotalInvestment();
    BigDecimal getTotalProfit();
}
