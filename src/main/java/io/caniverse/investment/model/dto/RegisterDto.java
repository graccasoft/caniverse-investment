package io.caniverse.investment.model.dto;

public record RegisterDto(
        String name,
        String phoneNumber,
        String email,
        String password,
        Long referrerId
) {

}
