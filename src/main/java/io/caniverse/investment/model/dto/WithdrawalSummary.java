package io.caniverse.investment.model.dto;

import java.math.BigDecimal;

public record WithdrawalSummary (
        BigDecimal pendingWithdrawals,
        BigDecimal approvedWithdrawals
){}
