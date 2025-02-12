package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.InvestorInvestment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvestorInvestmentRepository extends JpaRepository<InvestorInvestment, Long> {
    List<InvestorInvestment> findAllByInvestor(Investor investor);

    Optional<InvestorInvestment> findByInvestorAndId(Investor investor, Long id);
}