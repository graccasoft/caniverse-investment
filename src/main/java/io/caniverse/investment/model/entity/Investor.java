package io.caniverse.investment.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Investor extends BaseEntity {
    private String name;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne
    @JoinColumn(name = "referrer_id", nullable = true)
    private Investor referrer;

    @OneToMany(mappedBy = "referrer")
    private List<Investor> referredInvestors = new ArrayList<>();

    public Investor(){}
    public Investor(String name, String phoneNumber, User user){
        this.name = name;
        this.user = user;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Investor getReferrer() {
        return referrer;
    }

    public void setReferrer(Investor referrer) {
        this.referrer = referrer;
    }

    public List<Investor> getReferredInvestors() {
        return referredInvestors;
    }

    public void setReferredInvestors(List<Investor> referredInvestors) {
        this.referredInvestors = referredInvestors;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
