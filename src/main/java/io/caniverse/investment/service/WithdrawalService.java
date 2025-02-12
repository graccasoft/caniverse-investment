package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.dto.WithdrawDto;
import io.caniverse.investment.model.entity.User;
import io.caniverse.investment.model.entity.Withdrawal;
import io.caniverse.investment.model.enums.TransactionStatus;
import io.caniverse.investment.repository.InvestorInvestmentRepository;
import io.caniverse.investment.repository.WithdrawalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawalService {

    private final UserDetailsService userDetailsService;
    private final WithdrawalRepository withdrawalRepository;
    private final InvestorService investorService;
    private final InvestorInvestmentRepository investorInvestmentRepository;

    private final Logger logger = LoggerFactory.getLogger(WithdrawalService.class);

    public WithdrawalService(UserDetailsService userDetailsService,
                             WithdrawalRepository withdrawalRepository, InvestorService investorService, InvestorInvestmentRepository investorInvestmentRepository) {
        this.userDetailsService = userDetailsService;
        this.withdrawalRepository = withdrawalRepository;
        this.investorService = investorService;
        this.investorInvestmentRepository = investorInvestmentRepository;
    }

    public List<Withdrawal> getCurrentUserWithdrawals(Authentication authentication){
        var user = (User)userDetailsService.loadUserByUsername(authentication.getName());
        var investor = investorService.getInvestorFromUser(user);
        return withdrawalRepository.findAllByInvestorInvestment_Investor(investor);
    }

    public void save(WithdrawDto withdrawDto, Authentication authentication){
        var user = (User)userDetailsService.loadUserByUsername(authentication.getName());
        var investor = investorService.getInvestorFromUser(user);

        var investorInvestment = investorInvestmentRepository.findById(withdrawDto.investorInvestmentId())
                .orElseThrow(()-> new ValidationException("Invalid investor investment id"));

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
}
