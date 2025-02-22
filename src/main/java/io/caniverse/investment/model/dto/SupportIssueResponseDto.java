package io.caniverse.investment.model.dto;

import io.caniverse.investment.model.enums.SupportIssueStatus;

public record SupportIssueResponseDto(
        Long supportIssueId,
        String response,
        SupportIssueStatus status
) {
}
