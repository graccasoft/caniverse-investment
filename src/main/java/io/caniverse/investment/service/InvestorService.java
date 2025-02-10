package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.User;
import io.caniverse.investment.repository.InvestorRepository;
import org.springframework.stereotype.Service;

@Service
public class InvestorService {
    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public Investor getInvestorFromUser(User user){
        return investorRepository.findByUser(user).orElseThrow(()-> new RecordNotFoundException("Investor for user not found"));
    }
}
