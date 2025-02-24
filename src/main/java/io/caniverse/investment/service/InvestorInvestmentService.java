package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.dto.ApproveInvestmentDto;
import io.caniverse.investment.model.dto.InvestmentSummary;
import io.caniverse.investment.model.dto.PlaceInvestmentDto;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.InvestorInvestment;
import io.caniverse.investment.model.enums.InvestmentStatus;
import io.caniverse.investment.model.enums.InvestmentTerm;
import io.caniverse.investment.repository.InvestorInvestmentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvestorInvestmentService {

    private final InvestorInvestmentRepository investorInvestmentRepository;
    private final InvestorService investorService;
    private final InvestmentService investmentService;
    private final ReferralEarningService referralEarningService;

    public InvestorInvestmentService(InvestorInvestmentRepository investorInvestmentRepository, InvestorService investorService, InvestmentService investmentService, ReferralEarningService referralEarningService) {
        this.investorInvestmentRepository = investorInvestmentRepository;
        this.investorService = investorService;
        this.investmentService = investmentService;
        this.referralEarningService = referralEarningService;
    }

    public List<InvestorInvestment> getAllInvestments(){
        return investorInvestmentRepository.findAll();
    }

    public List<InvestorInvestment> getCurrentUserInvestments(Authentication authentication){
        return investorInvestmentRepository.findAllByInvestor(investorService.getInvestorFromAuthentication(authentication));
    }
    public List<InvestorInvestment> getInvestorInvestments(Long id){
        return investorInvestmentRepository.findAllByInvestor(investorService.getInvestor(id));
    }

    public void save(PlaceInvestmentDto placeInvestmentDto, Authentication authentication){
        doSave(placeInvestmentDto, investorService.getInvestorFromAuthentication(authentication));
    }

    public void save(PlaceInvestmentDto placeInvestmentDto, Long investorId){
        doSave(placeInvestmentDto, investorService.getInvestor(investorId));
    }

    public void doSave(PlaceInvestmentDto placeInvestmentDto, Investor investor){
        var investment = investmentService.get(placeInvestmentDto.investmentId());

        if(investment.getMinimumAmount().compareTo(placeInvestmentDto.amount()) > 0){
            throw new RecordNotFoundException("Amount is less than minimum amount");
        }
        var investorInvestment = new InvestorInvestment.Builder()
                .investment(investment)
                .transactionHash(placeInvestmentDto.transactionHash())
                .transactionNetwork(placeInvestmentDto.transactionNetwork())
                .amount(placeInvestmentDto.amount())
                .investor(investor)
                .status(InvestmentStatus.PENDING)
                .profitAmount(investment.getProfitAmountRate().multiply(placeInvestmentDto.amount()))
                .build();

        var withdrawalAmount = investorInvestment.getAmount().add(investorInvestment.getProfitAmount());
        if(investment.getInvestmentTerm().equals(InvestmentTerm.SHORT_TERM)){
            withdrawalAmount = withdrawalAmount.divide(BigDecimal.valueOf(investment.getPeriod()));
        }
        investorInvestment.setWithdrawalAmount(withdrawalAmount);

        investorInvestmentRepository.save(investorInvestment);

    }

    public InvestorInvestment getInvestorInvestment(Long id){
        return investorInvestmentRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Investment not found"));
    }

    @Transactional
    public void updateStatus(ApproveInvestmentDto approveInvestmentDto){
        var investorInvestment = investorInvestmentRepository.findById(approveInvestmentDto.id()).orElseThrow(()-> new RecordNotFoundException("Investment not found"));
        investorInvestment.setStatus(approveInvestmentDto.status());
        investorInvestmentRepository.save(investorInvestment);

        if(approveInvestmentDto.status().equals(InvestmentStatus.ACTIVE)){
            referralEarningService.saveEarning(investorInvestment);
        }
    }

    public InvestmentSummary getInvestorSummary(Authentication authentication){
       return investorInvestmentRepository.getInvestorSummary(investorService.getInvestorFromAuthentication(authentication).getId());
    }

    public InvestmentSummary getSummary(){
        return investorInvestmentRepository.getSummary();
    }

}
