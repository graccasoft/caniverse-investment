package io.caniverse.investment.model.dto;

import io.caniverse.investment.model.enums.TransactionStatus;

public record ApproveWithdrawalDto(
        Long id,
        String transactionHash,
        TransactionStatus transactionStatus
) {
}
