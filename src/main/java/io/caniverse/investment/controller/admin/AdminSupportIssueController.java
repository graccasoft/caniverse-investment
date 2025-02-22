package io.caniverse.investment.controller.admin;

import io.caniverse.investment.controller.BaseSupportIssueController;
import io.caniverse.investment.service.InvestorService;
import io.caniverse.investment.service.SupportIssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/support")
public class AdminSupportIssueController extends BaseSupportIssueController {
    public AdminSupportIssueController(InvestorService investorService, SupportIssueService supportIssueService) {
        super(investorService, supportIssueService);
    }

    @Override
    protected String getBaseUrl() {
        return "admin";
    }

    @GetMapping
    String allIssues(Model model){
        model.addAttribute("issues", supportIssueService.getAllIssues());
        return "admin/support-issues";
    }
}
