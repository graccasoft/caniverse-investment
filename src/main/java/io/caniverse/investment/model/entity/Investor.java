package io.caniverse.investment.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Investor extends BaseEntity {
    private String name;
    @OneToOne
    private User user;
    @OneToOne
    private Investor referrer;

    @OneToMany(mappedBy = "referrer")
    private List<Investor> referredInvestors = new ArrayList<>();
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
}
