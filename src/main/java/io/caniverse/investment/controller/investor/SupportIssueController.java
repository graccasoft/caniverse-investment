package io.caniverse.investment.controller.investor;

import io.caniverse.investment.controller.BaseSupportIssueController;
import io.caniverse.investment.model.entity.SupportIssue;
import io.caniverse.investment.service.InvestorService;
import io.caniverse.investment.service.SupportIssueService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/investor/support")
public class SupportIssueController extends BaseSupportIssueController {

    public SupportIssueController(InvestorService investorService, SupportIssueService supportIssueService) {
        super(investorService, supportIssueService);
    }

    @GetMapping
    String myIssues(Model model, Authentication authentication){
        model.addAttribute("issues", supportIssueService.getIssuesByInvestor(authentication));
        return "investor/support-issues";
    }

    @GetMapping("new")
    String newIssue(Model model, Authentication authentication, CsrfToken csrfToken){
        model.addAttribute("investor", investorService.getInvestorFromAuthentication(authentication));
        model.addAttribute("csrfToken",csrfToken);
        return "investor/support";
    }

    @PostMapping
    String saveIssue(@ModelAttribute SupportIssue supportIssue, Authentication authentication) {
        supportIssueService.saveIssue(supportIssue, authentication);
        return "redirect:/" + getBaseUrl() + "/support";
    }
}
