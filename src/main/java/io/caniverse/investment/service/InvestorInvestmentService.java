package io.caniverse.investment.service;

import io.caniverse.investment.model.entity.InvestorInvestment;
import io.caniverse.investment.model.entity.User;
import io.caniverse.investment.model.enums.InvestmentStatus;
import io.caniverse.investment.repository.InvestorInvestmentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorInvestmentService {

    private final UserDetailsServiceImpl userDetailsService;
    private final InvestorInvestmentRepository investorInvestmentRepository;
    private final InvestorService investorService;

    public InvestorInvestmentService(UserDetailsServiceImpl userDetailsService, InvestorInvestmentRepository investorInvestmentRepository, InvestorService investorService) {
        this.userDetailsService = userDetailsService;
        this.investorInvestmentRepository = investorInvestmentRepository;
        this.investorService = investorService;
    }

    public List<InvestorInvestment> getCurrentUserInvestments(Authentication authentication){
        User user = (User)userDetailsService.loadUserByUsername(authentication.getName());
        return investorInvestmentRepository.findAllByInvestor(investorService.getInvestorFromUser(user));
    }

    public InvestorInvestment save(InvestorInvestment investorInvestment, Authentication authentication){
        User user = (User)userDetailsService.loadUserByUsername(authentication.getName());
        investorInvestment.setInvestor( investorService.getInvestorFromUser(user) );
        investorInvestment.setStatus(InvestmentStatus.PENDING);

        return investorInvestmentRepository.save(investorInvestment);
    }
}
