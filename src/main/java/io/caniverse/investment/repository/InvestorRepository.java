package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
}