package io.caniverse.investment.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class ReferralEarning extends BaseEntity {
    private BigDecimal amount;
    @ManyToOne
    private Investor investor;
    @ManyToOne
    private InvestorInvestment investorInvestment;
    private BigDecimal rate;

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

    public InvestorInvestment getInvestorInvestment() {
        return investorInvestment;
    }

    public void setInvestorInvestment(InvestorInvestment investorInvestment) {
        this.investorInvestment = investorInvestment;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
