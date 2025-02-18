package io.caniverse.investment.model.dto;

import java.math.BigDecimal;

public record PlaceInvestmentDto(
        Long investmentId,
        String transactionHash,
        String transactionNetwork,
        BigDecimal amount
) {
}
