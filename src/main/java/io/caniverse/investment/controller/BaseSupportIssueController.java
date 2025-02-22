package io.caniverse.investment.controller;

import io.caniverse.investment.model.dto.SupportIssueResponseDto;
import io.caniverse.investment.service.InvestorService;
import io.caniverse.investment.service.SupportIssueService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class BaseSupportIssueController {

    protected final InvestorService investorService;
    protected final SupportIssueService supportIssueService;


    public BaseSupportIssueController(InvestorService investorService, SupportIssueService supportIssueService) {
        this.investorService = investorService;
        this.supportIssueService = supportIssueService;
    }

    protected String getBaseUrl() {
        return "investor";
    }

    @GetMapping("{id}")
    String singleIssue(Model model, @PathVariable Long id, CsrfToken csrfToken) {
        model.addAttribute("supportIssue", supportIssueService.getIssueById(id));
        model.addAttribute("csrfToken", csrfToken);
        return getBaseUrl() + "/support-issue";
    }


    @PostMapping("{id}/response")
    String saveResponse(@ModelAttribute SupportIssueResponseDto supportIssue, @PathVariable Long id) {
        supportIssueService.saveResponse(supportIssue, false);
        return "redirect:/" + getBaseUrl() + "/support";
    }

}
