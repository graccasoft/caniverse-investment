package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface InvestorRepository extends JpaRepository<Investor, Long>, QueryByExampleExecutor<Investor> {
    Optional<Investor> findByUser(User user);

    List<Investor> findByReferrer(Investor investor);


}