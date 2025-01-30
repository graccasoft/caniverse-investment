package io.caniverse.investment.model.dto;

public record RegisterDto(
        String name,
        String email,
        String password,
        Long referrerId
) {

}
