package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.dto.ApproveWithdrawalDto;
import io.caniverse.investment.model.dto.WithdrawDto;
import io.caniverse.investment.model.dto.WithdrawalSummary;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.Withdrawal;
import io.caniverse.investment.model.enums.InvestmentStatus;
import io.caniverse.investment.model.enums.TransactionStatus;
import io.caniverse.investment.repository.InvestorInvestmentRepository;
import io.caniverse.investment.repository.WithdrawalRepository;
import org.springframework.security.core.Authentication;
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

    public List<Withdrawal> getAll(){
        return withdrawalRepository.findAll();
    }

    public Withdrawal getWithdrawal(Long id){
        return withdrawalRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Withdrawal not found"));
    }

    public void save(WithdrawDto withdrawDto, Long investorId){
        doSave(withdrawDto, investorService.getInvestor(investorId));
    }
    public void save(WithdrawDto withdrawDto, Authentication authentication){
        doSave(withdrawDto, investorService.getInvestorFromAuthentication(authentication));
    }

    public void doSave(WithdrawDto withdrawDto, Investor investor){

        var investorInvestment = investorInvestmentRepository.findById(withdrawDto.investorInvestmentId())
                .orElseThrow(()-> new ValidationException("Invalid investor investment id"));

        if( !investorInvestment.getStatus().equals(InvestmentStatus.ACTIVE) ){
            throw new ValidationException("Investment has expired");
        }
        var investorInvestmentOptional = investorInvestmentRepository
                .findByInvestorAndId(  investor, withdrawDto.investorInvestmentId()  );
        if(investorInvestmentOptional.isEmpty()) {
            throw new ValidationException("Investment does not belong to investor");
        }

        var totalWithdrawals = withdrawalRepository.getTotalByInvestment(investorInvestment).orElse(BigDecimal.ZERO);
        var totalToBeWithdrawn = investorInvestment.getAmount().add(investorInvestment.getProfitAmount());
        if(  totalToBeWithdrawn.compareTo(totalWithdrawals) <= 0 ){
            throw new ValidationException("Can not make more withdrawals");
        }

        var withdrawal = new Withdrawal();
        withdrawal.setStatus(TransactionStatus.PENDING);
        withdrawal.setAddress(withdrawDto.address());
        withdrawal.setInvestorInvestment(investorInvestment);
        withdrawal.setAmount(investorInvestment.getWithdrawalAmount());

        withdrawalRepository.save(withdrawal);

    }

    public void updateStatus(ApproveWithdrawalDto approveWithdrawalDto){
        var withdrawal = withdrawalRepository.findById(approveWithdrawalDto.id()).orElseThrow(()-> new RecordNotFoundException("Withdrawal record not found"));
        withdrawal.setStatus(approveWithdrawalDto.transactionStatus());
        withdrawal.setTransactionHash(approveWithdrawalDto.transactionHash());
        withdrawalRepository.save(withdrawal);
    }

    public WithdrawalSummary getInvestorSummary(Authentication authentication){
        var investor = investorService.getInvestorFromAuthentication(authentication);
        var pending = withdrawalRepository.getTotalByInvestorAndStatus(investor, TransactionStatus.PENDING);
        var approved = withdrawalRepository.getTotalByInvestorAndStatus(investor, TransactionStatus.APPROVED);
        return new WithdrawalSummary(
                pending.orElse(BigDecimal.ZERO),
                approved.orElse(BigDecimal.ZERO)
        );
    }

    public WithdrawalSummary getSummary(){
        var pending = withdrawalRepository.getTotalByStatus(TransactionStatus.PENDING);
        var approved = withdrawalRepository.getTotalByStatus(TransactionStatus.APPROVED);
        return new WithdrawalSummary(
                pending.orElse(BigDecimal.ZERO),
                approved.orElse(BigDecimal.ZERO)
        );
    }
}
