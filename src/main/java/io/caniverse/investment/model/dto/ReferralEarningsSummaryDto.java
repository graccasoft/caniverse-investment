package io.caniverse.investment.model.dto;

import java.math.BigDecimal;

public record ReferralEarningsSummaryDto(
        BigDecimal totalEarnings,
        BigDecimal totalWithdrawals
) {
}
