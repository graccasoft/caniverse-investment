package io.caniverse.investment.controller.admin;

import io.caniverse.investment.model.dto.ApproveWithdrawalDto;
import io.caniverse.investment.model.enums.TransactionStatus;
import io.caniverse.investment.service.ReferralEarningService;
import io.caniverse.investment.service.WithdrawalService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/referral-withdrawals")
public class ReferralWithdrawalsController {

    private final ReferralEarningService withdrawalService;

    public ReferralWithdrawalsController(ReferralEarningService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }


    @PostMapping("status")
    String updateWithdrawalStatus(@ModelAttribute ApproveWithdrawalDto approveWithdrawalDto){
        withdrawalService.updateStatus(approveWithdrawalDto);
        return "redirect:/admin/referral-withdrawals";
    }

    @GetMapping
    String withdrawals(Model model, @RequestParam(required = false) TransactionStatus status){
        var withdrawalStatus = status == null ? TransactionStatus.PENDING : status;
        model.addAttribute("withdrawals", withdrawalService.getAllWithdrawals( withdrawalStatus  ));
        model.addAttribute("status", withdrawalStatus);
        return "admin/referral-withdrawals";
    }

    @GetMapping("{id}")
    String withdrawal(@PathVariable Long id, Model model, CsrfToken csrfToken){
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("withdrawal", withdrawalService.getWithdrawal(id));
        return "admin/referral-withdrawal";
    }
}
