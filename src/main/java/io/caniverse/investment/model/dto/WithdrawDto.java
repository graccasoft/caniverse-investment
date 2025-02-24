package io.caniverse.investment.model.dto;

import java.math.BigDecimal;

public record WithdrawDto(
        BigDecimal amount,
        String address,
        String cnvAddress,
        Long investorInvestmentId
) {


}
