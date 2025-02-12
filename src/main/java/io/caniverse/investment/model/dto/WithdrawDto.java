package io.caniverse.investment.model.dto;

public record WithdrawDto(
        String address,
        Long investorInvestmentId
) {
    public WithdrawDto(){
        this(null, null);
    }

}
