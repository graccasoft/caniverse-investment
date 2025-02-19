package io.caniverse.investment.controller.admin;

import io.caniverse.investment.service.InvestorInvestmentService;
import io.caniverse.investment.service.WithdrawalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final InvestorInvestmentService investorInvestmentService;
    private final WithdrawalService withdrawalService;

    public AdminController(InvestorInvestmentService investorInvestmentService, WithdrawalService withdrawalService) {
        this.investorInvestmentService = investorInvestmentService;
        this.withdrawalService = withdrawalService;
    }

    @GetMapping({"", "dashboard"})
    String dashboard(Model model) {
        model.addAttribute("investmentSummary", investorInvestmentService.getSummary());
        model.addAttribute("withdrawalSummary", withdrawalService.getSummary());
        return "admin/dashboard";
    }


}
