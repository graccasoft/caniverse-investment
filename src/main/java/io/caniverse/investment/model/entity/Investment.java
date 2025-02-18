package io.caniverse.investment.model.entity;

import io.caniverse.investment.model.enums.InvestmentTerm;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

@Entity
public class Investment extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal minimumAmount;
    private BigDecimal profitAmountRate;
    private Integer period;

    @Enumerated(EnumType.STRING)
    private InvestmentTerm investmentTerm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public BigDecimal getProfitAmountRate() {
        return profitAmountRate;
    }

    public void setProfitAmountRate(BigDecimal profitAmountRate) {
        this.profitAmountRate = profitAmountRate;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public InvestmentTerm getInvestmentTerm() {
        return investmentTerm;
    }

    public void setInvestmentTerm(InvestmentTerm investmentTerm) {
        this.investmentTerm = investmentTerm;
    }
}
