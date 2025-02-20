package io.caniverse.investment.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class SupportIssueResponse extends BaseEntity {
    private String response;
    private boolean fromAdmin;
    @ManyToOne
    private SupportIssue supportIssue;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public SupportIssue getSupportIssue() {
        return supportIssue;
    }

    public void setSupportIssue(SupportIssue supportIssue) {
        this.supportIssue = supportIssue;
    }

    public boolean isFromAdmin() {
        return fromAdmin;
    }

    public void setFromAdmin(boolean fromAdmin) {
        this.fromAdmin = fromAdmin;
    }
}
