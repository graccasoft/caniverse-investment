package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.SupportIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportIssueRepository extends JpaRepository<SupportIssue, Long> {
    List<SupportIssue> findByInvestor(Investor investor);
}