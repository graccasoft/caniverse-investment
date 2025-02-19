package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.User;
import io.caniverse.investment.repository.InvestorRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {
    private final InvestorRepository investorRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public InvestorService(InvestorRepository investorRepository, UserDetailsServiceImpl userDetailsService) {
        this.investorRepository = investorRepository;
        this.userDetailsService = userDetailsService;
    }

    public Investor getInvestorFromUser(User user) {
        return investorRepository.findByUser(user).orElseThrow(() -> new RecordNotFoundException("Investor for user not found"));
    }

    public Investor getInvestorFromAuthentication(Authentication authentication) {
        var user = (User) userDetailsService.loadUserByUsername(authentication.getName());
        return getInvestorFromUser(user);
    }

    public List<Investor> getMyTeam(Authentication authentication) {
        var investor = getInvestorFromAuthentication(authentication);
        return investorRepository.findByReferrer(investor);
    }

    public Investor getInvestor(Long id) {
        return investorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Investor not found"));
    }

    public List<Investor> findInvestors(Investor investor) {
        var matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(investor, matcher);
        return investorRepository.findAll(example);
    }
}
