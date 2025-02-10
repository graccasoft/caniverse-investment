package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}