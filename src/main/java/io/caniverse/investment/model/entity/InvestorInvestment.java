package io.caniverse.investment.model.entity;

import io.caniverse.investment.model.enums.InvestmentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class InvestorInvestment extends BaseEntity {
    @ManyToOne
    private Investment investment;
    @ManyToOne
    private Investor investor;

    private InvestmentStatus status;
    private String transactionHash;
    private String transactionNetwork;

    private BigDecimal amount;
    private BigDecimal profitAmount;
    private BigDecimal withdrawalAmount;

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public InvestmentStatus getStatus() {
        return status;
    }

    public void setStatus(InvestmentStatus status) {
        this.status = status;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public String getTransactionNetwork() {
        return transactionNetwork;
    }

    public void setTransactionNetwork(String transactionNetwork) {
        this.transactionNetwork = transactionNetwork;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(BigDecimal profitAmount) {
        this.profitAmount = profitAmount;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    //builder pattern
    public static class Builder {
        private Investment investment;
        private Investor investor;
        private InvestmentStatus status;
        private String transactionHash;
        private String transactionNetwork;
        private BigDecimal amount;
        private BigDecimal profitAmount;
        private BigDecimal withdrawalAmount;

        public Builder investment(Investment investment) {
            this.investment = investment;
            return this;
        }

        public Builder investor(Investor investor) {
            this.investor = investor;
            return this;
        }

        public Builder status(InvestmentStatus status) {
            this.status = status;
            return this;
        }

        public Builder transactionHash(String transactionHash) {
            this.transactionHash = transactionHash;
            return this;
        }

        public Builder transactionNetwork(String transactionNetwork) {
            this.transactionNetwork = transactionNetwork;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder profitAmount(BigDecimal profitAmount) {
            this.profitAmount = profitAmount;
            return this;
        }
        public Builder withdrawalAMount(BigDecimal withdrawalAmount) {
            this.withdrawalAmount = withdrawalAmount;
            return this;
        }

        public InvestorInvestment build() {
            InvestorInvestment investorInvestment = new InvestorInvestment();
            investorInvestment.setInvestment(investment);
            investorInvestment.setInvestor(investor);
            investorInvestment.setStatus(status);
            investorInvestment.setTransactionHash(transactionHash);
            investorInvestment.setTransactionNetwork(transactionNetwork);
            investorInvestment.setAmount(amount);
            investorInvestment.setProfitAmount(profitAmount);
            investorInvestment.setWithdrawalAmount(withdrawalAmount);
            return investorInvestment;
        }
    }
}
