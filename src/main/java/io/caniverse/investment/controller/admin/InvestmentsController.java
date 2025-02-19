package io.caniverse.investment.controller.admin;

import io.caniverse.investment.model.dto.ApproveInvestmentDto;
import io.caniverse.investment.service.InvestorInvestmentService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/investments")
public class InvestmentsController {

    private final InvestorInvestmentService investorInvestmentService;

    public InvestmentsController(InvestorInvestmentService investorInvestmentService) {
        this.investorInvestmentService = investorInvestmentService;
    }
    @GetMapping
    String investments(Model model){
        model.addAttribute("investments", investorInvestmentService.getAllInvestments());
        return "admin/investments";
    }

    @GetMapping("{id}")
    String investment(@PathVariable Long id, Model model, CsrfToken csrfToken){
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("investment", investorInvestmentService.getInvestorInvestment(id));
        return "admin/investment";
    }


    @PostMapping("/status")
    String updateInvestmentStatus(@ModelAttribute ApproveInvestmentDto approveInvestmentDto){
        investorInvestmentService.updateStatus(approveInvestmentDto);
        return "redirect:/admin/investments";
    }
}
