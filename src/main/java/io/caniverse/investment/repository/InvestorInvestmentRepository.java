package io.caniverse.investment.repository;

import io.caniverse.investment.model.dto.InvestmentSummary;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.InvestorInvestment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvestorInvestmentRepository extends JpaRepository<InvestorInvestment, Long> {
    List<InvestorInvestment> findAllByInvestor(Investor investor);

    Optional<InvestorInvestment> findByInvestorAndId(Investor investor, Long id);

    @Query(
            """
                    SELECT SUM(i.investment.amount) AS totalInvestment, SUM(i.investment.profitAmount) AS totalProfit
                    FROM InvestorInvestment i
                    WHERE i.investor.id = :investorId
                    GROUP BY i.investor
                    """)
    InvestmentSummary getInvestorSummary(@Param("investorId") Long investorId);

    @Query(
            """
                    SELECT SUM(i.investment.amount) AS totalInvestment, SUM(i.investment.profitAmount) AS totalProfit
                    FROM InvestorInvestment i
                    """)
    InvestmentSummary getSummary();
}