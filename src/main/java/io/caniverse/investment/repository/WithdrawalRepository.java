package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.Investment;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.User;
import io.caniverse.investment.model.entity.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    List<Withdrawal> findAllByInvestorInvestment_Investor(Investor investor);
    List<Withdrawal> findAllByInvestorInvestment_Investment(Investment investment);
}