package io.caniverse.investment.model.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Investment extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal amount;
    private BigDecimal profitAmount;
    private Integer period;
    private Integer numberOfWithdrawals;
    private BigDecimal withdrawalAmount;

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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getNumberOfWithdrawals() {
        return numberOfWithdrawals;
    }

    public void setNumberOfWithdrawals(Integer numberOfWithdrawals) {
        this.numberOfWithdrawals = numberOfWithdrawals;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }
}
