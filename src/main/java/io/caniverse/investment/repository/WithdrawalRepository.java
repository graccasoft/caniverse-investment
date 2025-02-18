package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.*;
import io.caniverse.investment.model.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    List<Withdrawal> findAllByInvestorInvestment_Investor(Investor investor);
    List<Withdrawal> findAllByInvestorInvestment_Investment(Investment investment);

    @Query("SELECT SUM(w.amount) FROM Withdrawal w WHERE w.investorInvestment.investor = :investor AND w.status = :status")
    BigDecimal getTotalByInvestorAndStatus(@Param("investor") Investor investor, @Param("status") TransactionStatus status);

    @Query("SELECT SUM(w.amount) FROM Withdrawal w WHERE w.status = :status")
    BigDecimal getTotalByStatus(@Param("status") TransactionStatus status);

    @Query("SELECT SUM(w.amount) FROM Withdrawal w WHERE w.investorInvestment = :investment")
    BigDecimal getTotalByInvestment(@Param("investment") InvestorInvestment investor);
}