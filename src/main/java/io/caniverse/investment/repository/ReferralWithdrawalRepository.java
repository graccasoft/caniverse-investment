package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.ReferralWithdrawal;
import io.caniverse.investment.model.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ReferralWithdrawalRepository extends JpaRepository<ReferralWithdrawal, Long> {
    List<ReferralWithdrawal> findAllByInvestor_Id(Long id);

    @Query("SELECT SUM(amount) FROM ReferralWithdrawal WHERE investor.id = ?1")
    Optional<BigDecimal> getTotalWithdrawalsByInvestor_Id(Long id);

    List<ReferralWithdrawal> findAllByStatus(TransactionStatus status);
}
