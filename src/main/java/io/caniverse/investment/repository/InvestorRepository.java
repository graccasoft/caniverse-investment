package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
    Optional<Investor> findByUser(User user);
}