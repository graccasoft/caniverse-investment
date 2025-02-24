package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.ReferralEarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ReferralEarningRepository extends JpaRepository<ReferralEarning, Long> {
    List<ReferralEarning> findAllByInvestor_Id(Long id);

    @Query("SELECT SUM(amount) FROM ReferralEarning WHERE investor.id = ?1")
    Optional<BigDecimal> getTotalEarningsByInvestor_Id(Long id);
}