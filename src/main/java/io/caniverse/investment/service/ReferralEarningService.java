package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.dto.ApproveWithdrawalDto;
import io.caniverse.investment.model.dto.ReferralEarningsSummaryDto;
import io.caniverse.investment.model.dto.WithdrawDto;
import io.caniverse.investment.model.entity.InvestorInvestment;
import io.caniverse.investment.model.entity.ReferralEarning;
import io.caniverse.investment.model.entity.ReferralWithdrawal;
import io.caniverse.investment.model.enums.TransactionStatus;
import io.caniverse.investment.repository.ReferralEarningRepository;
import io.caniverse.investment.repository.ReferralWithdrawalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReferralEarningService {
    private final BigDecimal firstPercentage = BigDecimal.valueOf(0.05);
    private final BigDecimal secondPercentage = BigDecimal.valueOf(0.02);
    private final ReferralEarningRepository referralEarningRepository;
    private final ReferralWithdrawalRepository referralWithdrawalRepository;
    private final InvestorService investorService;
    private final Logger log = LoggerFactory.getLogger(ReferralEarningService.class);

    public ReferralEarningService(ReferralEarningRepository referralEarningRepository, ReferralWithdrawalRepository referralWithdrawalRepository, InvestorService investorService) {
        this.referralEarningRepository = referralEarningRepository;
        this.referralWithdrawalRepository = referralWithdrawalRepository;
        this.investorService = investorService;
    }

    public void saveEarning(InvestorInvestment investorInvestment){
        var firstEarner = investorInvestment.getInvestor().getReferrer();
        if(firstEarner == null){
            return;
        }

        List<ReferralEarning> earnings = new ArrayList<>();
        var firstEarning = new ReferralEarning();
        firstEarning.setInvestor(firstEarner);
        firstEarning.setInvestorInvestment(investorInvestment);
        firstEarning.setRate(firstPercentage);
        firstEarning.setAmount(investorInvestment.getAmount().multiply(firstPercentage));
        earnings.add(firstEarning);

        var secondEarner = firstEarner.getReferrer();
        if(secondEarner != null){
            var secondEarning = new ReferralEarning();
            secondEarning.setInvestor(secondEarner);
            secondEarning.setInvestorInvestment(investorInvestment);
            secondEarning.setRate(secondPercentage);
            secondEarning.setAmount(investorInvestment.getAmount().multiply(secondPercentage));
            earnings.add(secondEarning);
        }

        referralEarningRepository.saveAll(earnings);
    }

    public List<ReferralEarning> getInvestorEarnings(Authentication authentication){
        var investor = investorService.getInvestorFromAuthentication(authentication);
        return referralEarningRepository.findAllByInvestor_Id(investor.getId());
    }
    public List<ReferralWithdrawal> getInvestorWithdrawals(Long id){
        return referralWithdrawalRepository.findAllByInvestor_Id(id);
    }

    public void makeWithdrawal(WithdrawDto withdrawDto, Authentication authentication){
        var amount = withdrawDto.amount();

        var investor = investorService.getInvestorFromAuthentication(authentication);
        var totalEarnings = referralEarningRepository.getTotalEarningsByInvestor_Id(investor.getId()).orElse(BigDecimal.ZERO);
        var totalWithdrawals = referralWithdrawalRepository.getTotalWithdrawalsByInvestor_Id(investor.getId()).orElse(BigDecimal.ZERO);

        log.info("Total earnings: {}, Total withdrawals: {}", totalEarnings, totalWithdrawals);
        if( amount.compareTo(BigDecimal.ZERO) <=0 || totalEarnings.subtract(totalWithdrawals).compareTo(amount) < 0){
            throw new ValidationException("Insufficient balance");
        }

        var withdrawal = new ReferralWithdrawal();
        withdrawal.setAmount(amount);
        withdrawal.setInvestor(investor);
        withdrawal.setAddress(withdrawDto.address());
        withdrawal.setCnvAddress(withdrawal.getCnvAddress());
        withdrawal.setStatus(TransactionStatus.PENDING);
        referralWithdrawalRepository.save(withdrawal);
    }

    public ReferralEarningsSummaryDto getSummary(Authentication authentication){
        var investor = investorService.getInvestorFromAuthentication(authentication);
        return new ReferralEarningsSummaryDto(
                referralEarningRepository.getTotalEarningsByInvestor_Id(investor.getId()).orElse(BigDecimal.ZERO),
                referralWithdrawalRepository.getTotalWithdrawalsByInvestor_Id(investor.getId()).orElse(BigDecimal.ZERO)
        );
    }

    public List<ReferralWithdrawal> getAllWithdrawals(TransactionStatus status){
        return referralWithdrawalRepository.findAllByStatus(status);
    }

    public ReferralWithdrawal getWithdrawal(Long id){
        return referralWithdrawalRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Withdrawal not found"));
    }

    public void updateStatus(ApproveWithdrawalDto approveWithdrawalDto){
        var withdrawal = referralWithdrawalRepository.findById(approveWithdrawalDto.id()).orElseThrow(()-> new RecordNotFoundException("Withdrawal record not found"));
        withdrawal.setStatus(approveWithdrawalDto.transactionStatus());
        withdrawal.setTransactionHash(approveWithdrawalDto.transactionHash());
        referralWithdrawalRepository.save(withdrawal);
    }
}
