package io.caniverse.investment.model.entity;

import io.caniverse.investment.model.enums.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class ReferralWithdrawal extends BaseEntity {
    private BigDecimal amount;
    @ManyToOne
    private Investor investor;
    private String address;
    private String cnvAddress;

    private TransactionStatus status;
    private String transactionHash;
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
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
