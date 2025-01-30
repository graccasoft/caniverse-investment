package io.caniverse.investment.model.entity;

import io.caniverse.investment.model.enums.InvestmentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class InvestorInvestment extends BaseEntity {
    @ManyToOne
    private Investment investment;
    @ManyToOne
    private Investor investor;

    private InvestmentStatus status;
    private String transactionHash;
    private String transactionNetwork;

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
}
