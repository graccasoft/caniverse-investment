package io.caniverse.investment.model.dto;

import io.caniverse.investment.model.enums.InvestmentStatus;

public record ApproveInvestmentDto(
        Long id,
        InvestmentStatus status
) {
}
