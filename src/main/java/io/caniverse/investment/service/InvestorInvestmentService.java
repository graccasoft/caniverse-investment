package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
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
        var user = (User)userDetailsService.loadUserByUsername(authentication.getName());
        return investorInvestmentRepository.findAllByInvestor(investorService.getInvestorFromUser(user));
    }

    public void save(InvestorInvestment investorInvestment, Authentication authentication){
        var user = (User)userDetailsService.loadUserByUsername(authentication.getName());
        investorInvestment.setInvestor( investorService.getInvestorFromUser(user) );
        investorInvestment.setStatus(InvestmentStatus.PENDING);

        investorInvestmentRepository.save(investorInvestment);
    }

    public InvestorInvestment getInvestorInvestment(Long id){
        return investorInvestmentRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Investment not found"));
    }

    public void updateStatus(Long id, InvestmentStatus status){
        var investorInvestment = investorInvestmentRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Investment not found"));
        investorInvestment.setStatus(status);
        investorInvestmentRepository.save(investorInvestment);
    }
}
