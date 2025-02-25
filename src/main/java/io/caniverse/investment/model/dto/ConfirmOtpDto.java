package io.caniverse.investment.model.dto;

public record ConfirmOtpDto(
        String username,
        String otp,
        String password
) {
}
