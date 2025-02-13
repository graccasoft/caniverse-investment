package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.dto.WithdrawDto;
import io.caniverse.investment.model.dto.WithdrawalSummary;
import io.caniverse.investment.model.entity.User;
import io.caniverse.investment.model.entity.Withdrawal;
import io.caniverse.investment.model.enums.InvestmentStatus;
import io.caniverse.investment.model.enums.TransactionStatus;
import io.caniverse.investment.repository.InvestorInvestmentRepository;
import io.caniverse.investment.repository.WithdrawalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;
    private final InvestorService investorService;
    private final InvestorInvestmentRepository investorInvestmentRepository;


    public WithdrawalService(WithdrawalRepository withdrawalRepository, InvestorService investorService, InvestorInvestmentRepository investorInvestmentRepository) {
        this.withdrawalRepository = withdrawalRepository;
        this.investorService = investorService;
        this.investorInvestmentRepository = investorInvestmentRepository;
    }

    public List<Withdrawal> getCurrentUserWithdrawals(Authentication authentication){
        var investor = investorService.getInvestorFromAuthentication(authentication);
        return withdrawalRepository.findAllByInvestorInvestment_Investor(investor);
    }

    public void save(WithdrawDto withdrawDto, Authentication authentication){
        var investor = investorService.getInvestorFromAuthentication(authentication);

        var investorInvestment = investorInvestmentRepository.findById(withdrawDto.investorInvestmentId())
                .orElseThrow(()-> new ValidationException("Invalid investor investment id"));

        if( !investorInvestment.getStatus().equals(InvestmentStatus.ACTIVE) ){
            throw new ValidationException("Investment has expired");
        }
        investorInvestmentRepository
                .findByInvestorAndId(  investor, withdrawDto.investorInvestmentId()  )
                .orElseThrow(()-> new ValidationException("Investment does not belong to investor"));

        List<Withdrawal> previousWithdrawals = withdrawalRepository
                .findAllByInvestorInvestment_Investment(investorInvestment.getInvestment());
        if(  previousWithdrawals.size() >= investorInvestment.getInvestment().getNumberOfWithdrawals()  ){
            throw new ValidationException("Can not make more withdrawals");
        }

        var withdrawal = new Withdrawal();
        withdrawal.setStatus(TransactionStatus.PENDING);
        withdrawal.setAddress(withdrawDto.address());
        withdrawal.setInvestorInvestment(investorInvestment);
        withdrawal.setAmount(investorInvestment.getInvestment().getWithdrawalAmount());

        withdrawalRepository.save(withdrawal);

    }

    public void updateStatus(Long id, TransactionStatus status){
        var withdrawal = withdrawalRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Withdrawal record not found"));
        withdrawal.setStatus(status);
        withdrawalRepository.save(withdrawal);
    }

    public WithdrawalSummary getInvestorSummary(Authentication authentication){
        var investor = investorService.getInvestorFromAuthentication(authentication);
        var pending = withdrawalRepository.getTotalByInvestorAndStatus(investor, TransactionStatus.PENDING);
        var approved = withdrawalRepository.getTotalByInvestorAndStatus(investor, TransactionStatus.APPROVED);
        return new WithdrawalSummary(
                pending == null ? BigDecimal.ZERO : pending,
                approved == null ? BigDecimal.ZERO : approved
        );
    }
}
