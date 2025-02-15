package io.caniverse.investment.controller;

import io.caniverse.investment.model.dto.ApproveInvestmentDto;
import io.caniverse.investment.model.dto.ApproveWithdrawalDto;
import io.caniverse.investment.service.InvestorInvestmentService;
import io.caniverse.investment.service.WithdrawalService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final InvestorInvestmentService investorInvestmentService;
    private final WithdrawalService withdrawalService;

    public AdminController(InvestorInvestmentService investorInvestmentService, WithdrawalService withdrawalService) {
        this.investorInvestmentService = investorInvestmentService;
        this.withdrawalService = withdrawalService;
    }

    @GetMapping({"","dashboard"})
    String dashboard(Model model){
        model.addAttribute("investmentSummary", investorInvestmentService.getSummary());
        model.addAttribute("withdrawalSummary", withdrawalService.getSummary());
        return "admin/dashboard";
    }

    @GetMapping("investments")
    String investments(Model model){
        model.addAttribute("investments", investorInvestmentService.getAllInvestments());
        return "admin/investments";
    }

    @GetMapping("investments/{id}")
    String investment(@PathVariable Long id, Model model,CsrfToken csrfToken){
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("investment", investorInvestmentService.getInvestorInvestment(id));
        return "admin/investment";
    }

    @GetMapping("withdrawals")
    String withdrawals(Model model){
        model.addAttribute("withdrawals", withdrawalService.getAll());
        return "admin/withdrawals";
    }

    @GetMapping("withdrawals/{id}")
    String withdrawal(@PathVariable Long id, Model model, CsrfToken csrfToken){
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("withdrawal", withdrawalService.getWithdrawal(id));
        return "admin/withdrawal";
    }
    @PostMapping("investment-status")
    String updateInvestmentStatus(@ModelAttribute ApproveInvestmentDto approveInvestmentDto){
        investorInvestmentService.updateStatus(approveInvestmentDto);
        return "redirect:/admin/investments";
    }

    @PostMapping("withdrawal-status")
    String updateWithdrawalStatus(@ModelAttribute ApproveWithdrawalDto approveWithdrawalDto){
        withdrawalService.updateStatus(approveWithdrawalDto);
        return "redirect:/admin/withdrawals";
    }

}
