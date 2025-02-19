package io.caniverse.investment.controller.admin;

import io.caniverse.investment.model.dto.ApproveWithdrawalDto;
import io.caniverse.investment.model.dto.WithdrawDto;
import io.caniverse.investment.service.InvestorInvestmentService;
import io.caniverse.investment.service.InvestorService;
import io.caniverse.investment.service.WithdrawalService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/withdrawals")
public class WithdrawalsController {

    private final WithdrawalService withdrawalService;

    public WithdrawalsController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }


    @PostMapping("status")
    String updateWithdrawalStatus(@ModelAttribute ApproveWithdrawalDto approveWithdrawalDto){
        withdrawalService.updateStatus(approveWithdrawalDto);
        return "redirect:/admin/withdrawals";
    }

    @GetMapping
    String withdrawals(Model model){
        model.addAttribute("withdrawals", withdrawalService.getAll());
        return "admin/withdrawals";
    }

    @GetMapping("{id}")
    String withdrawal(@PathVariable Long id, Model model, CsrfToken csrfToken){
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("withdrawal", withdrawalService.getWithdrawal(id));
        return "admin/withdrawal";
    }
}
