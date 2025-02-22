package io.caniverse.investment.model.entity;

import io.caniverse.investment.model.enums.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class Withdrawal extends BaseEntity {
    @ManyToOne
    private InvestorInvestment investorInvestment;
    private BigDecimal amount;

    private TransactionStatus status;
    private String address;
    private String cnvAddress;
    private String transactionHash;

    public InvestorInvestment getInvestorInvestment() {
        return investorInvestment;
    }

    public void setInvestorInvestment(InvestorInvestment investorInvestment) {
        this.investorInvestment = investorInvestment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnvAddress() {
        return cnvAddress;
    }

    public void setCnvAddress(String cnvAddress) {
        this.cnvAddress = cnvAddress;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }
}
