package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.dto.ApproveInvestmentDto;
import io.caniverse.investment.model.dto.InvestmentSummary;
import io.caniverse.investment.model.entity.InvestorInvestment;
import io.caniverse.investment.model.enums.InvestmentStatus;
import io.caniverse.investment.repository.InvestorInvestmentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorInvestmentService {

    private final InvestorInvestmentRepository investorInvestmentRepository;
    private final InvestorService investorService;

    public InvestorInvestmentService(InvestorInvestmentRepository investorInvestmentRepository, InvestorService investorService) {
        this.investorInvestmentRepository = investorInvestmentRepository;
        this.investorService = investorService;
    }

    public List<InvestorInvestment> getAllInvestments(){
        return investorInvestmentRepository.findAll();
    }

    public List<InvestorInvestment> getCurrentUserInvestments(Authentication authentication){
        return investorInvestmentRepository.findAllByInvestor(investorService.getInvestorFromAuthentication(authentication));
    }

    public void save(InvestorInvestment investorInvestment, Authentication authentication){
        investorInvestment.setInvestor( investorService.getInvestorFromAuthentication(authentication) );
        investorInvestment.setStatus(InvestmentStatus.PENDING);

        investorInvestmentRepository.save(investorInvestment);
    }

    public InvestorInvestment getInvestorInvestment(Long id){
        return investorInvestmentRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Investment not found"));
    }

    public void updateStatus(ApproveInvestmentDto approveInvestmentDto){
        var investorInvestment = investorInvestmentRepository.findById(approveInvestmentDto.id()).orElseThrow(()-> new RecordNotFoundException("Investment not found"));
        investorInvestment.setStatus(approveInvestmentDto.status());
        investorInvestmentRepository.save(investorInvestment);
    }

    public InvestmentSummary getInvestorSummary(Authentication authentication){
       return investorInvestmentRepository.getInvestorSummary(investorService.getInvestorFromAuthentication(authentication).getId());
    }

    public InvestmentSummary getSummary(){
        return investorInvestmentRepository.getSummary();
    }

}
