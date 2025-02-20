package io.caniverse.investment.controller.investor;

import io.caniverse.investment.model.dto.SupportIssueResponseDto;
import io.caniverse.investment.model.entity.SupportIssue;
import io.caniverse.investment.service.InvestorService;
import io.caniverse.investment.service.SupportIssueService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/investor/support")
public class SupportIssueController {

    private final InvestorService investorService;
    private final SupportIssueService supportIssueService;

    public SupportIssueController(InvestorService investorService, SupportIssueService supportIssueService) {
        this.investorService = investorService;
        this.supportIssueService = supportIssueService;
    }

    @GetMapping
    String myIssues(Model model, Authentication authentication){
        model.addAttribute("issues", supportIssueService.getIssuesByInvestor(authentication));
        return "investor/support-issues";
    }
    @GetMapping("{id}")
    String myIssue(Model model, @PathVariable Long id, CsrfToken csrfToken){
        model.addAttribute("supportIssue", supportIssueService.getIssueById(id));
        model.addAttribute("csrfToken",csrfToken);
        return "investor/support-issue";
    }

    @GetMapping("new")
    String newIssue(Model model, Authentication authentication, CsrfToken csrfToken){
        model.addAttribute("investor", investorService.getInvestorFromAuthentication(authentication));
        model.addAttribute("csrfToken",csrfToken);
        return "investor/support";
    }

    @PostMapping
    String saveIssue(@ModelAttribute SupportIssue supportIssue, Authentication authentication){
        supportIssueService.saveIssue(supportIssue, authentication);
        return "redirect:/investor/support";
    }

    @PostMapping("{id}/response")
    String saveResponse(@ModelAttribute SupportIssueResponseDto supportIssue, @PathVariable Long id){
        supportIssueService.saveResponse(supportIssue, false);
        return "redirect:/investor/support";
    }

}
