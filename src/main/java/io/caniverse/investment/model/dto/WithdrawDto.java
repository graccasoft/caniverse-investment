package io.caniverse.investment.model.dto;

public record WithdrawDto(
        String address,
        String cnvAddress,
        Long investorInvestmentId
) {
    public WithdrawDto(){
        this(null, null, null);
    }

}
