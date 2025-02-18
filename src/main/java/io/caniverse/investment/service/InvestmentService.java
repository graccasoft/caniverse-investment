package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.entity.Investment;
import io.caniverse.investment.repository.InvestmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {
    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public List<Investment> getAll(){
        return investmentRepository.findAll();
    }

    public Investment get(Long id){
        return investmentRepository.findById(id).orElseThrow( ()-> new RecordNotFoundException("Investment not found"));
    }
}
