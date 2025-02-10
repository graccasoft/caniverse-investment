package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.InvestorInvestment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestorInvestmentRepository extends JpaRepository<InvestorInvestment, Long> {
    List<InvestorInvestment> findAllByInvestor(Investor investor);
}