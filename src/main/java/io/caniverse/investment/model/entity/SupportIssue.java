package io.caniverse.investment.model.entity;

import io.caniverse.investment.model.enums.SupportIssueStatus;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SupportIssue extends BaseEntity {
    private String subject;
    @Column(length = 3000)
    private String description;

    @ManyToOne
    private Investor investor;

    @Enumerated(EnumType.STRING)
    private SupportIssueStatus status;

    @OneToMany(mappedBy = "supportIssue", cascade = CascadeType.ALL)
    private Set<SupportIssueResponse> responses = new HashSet<>();
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Set<SupportIssueResponse> getResponses() {
        return responses;
    }

    public void setResponses(Set<SupportIssueResponse> responses) {
        this.responses = responses;
    }

    public SupportIssueStatus getStatus() {
        return status;
    }

    public void setStatus(SupportIssueStatus status) {
        this.status = status;
    }
}
