package io.caniverse.investment.controller;

import io.caniverse.investment.model.dto.WithdrawDto;
import io.caniverse.investment.model.entity.InvestorInvestment;
import io.caniverse.investment.service.InvestmentService;
import io.caniverse.investment.service.InvestorInvestmentService;
import io.caniverse.investment.service.InvestorService;
import io.caniverse.investment.service.WithdrawalService;
import io.caniverse.investment.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/investor")
public class InvestorController {

    private final InvestmentService investmentService;
    private final InvestorInvestmentService investorInvestmentService;
    private final WithdrawalService withdrawalService;
    private final InvestorService investorService;

    public InvestorController(InvestmentService investmentService, InvestorInvestmentService investorInvestmentService, WithdrawalService withdrawalService, InvestorService investorService) {
        this.investmentService = investmentService;
        this.investorInvestmentService = investorInvestmentService;
        this.withdrawalService = withdrawalService;
        this.investorService = investorService;
    }

    @GetMapping
    String dashboard(Model model, Authentication authentication){
        model.addAttribute("investments", investmentService.getAll());
        model.addAttribute("investmentSummary", investorInvestmentService.getInvestorSummary(authentication));
        model.addAttribute("withdrawalSummary", withdrawalService.getInvestorSummary(authentication));
        model.addAttribute("investor", investorService.getInvestorFromAuthentication(authentication));
        return "investor/dashboard";
    }

    @GetMapping("withdrawals")
    String withdrawals(Model model, Authentication authentication){
        model.addAttribute("withdrawals", withdrawalService.getCurrentUserWithdrawals(authentication));
        model.addAttribute("investor", investorService.getInvestorFromAuthentication(authentication));
        return "investor/withdrawals";
    }

    @GetMapping("withdraw")
    String withdraw(Model model, Authentication authentication, CsrfToken csrfToken){
        model.addAttribute("investments", investorInvestmentService.getCurrentUserInvestments(authentication));
        model.addAttribute("investor", investorService.getInvestorFromAuthentication(authentication));
        model.addAttribute("csrfToken",csrfToken);
        model.addAttribute("withdrawal", new WithdrawDto());
        return "investor/withdraw";
    }
    @PostMapping("withdraw")
    String doWithdraw(@ModelAttribute WithdrawDto withdrawal, Authentication authentication){
        withdrawalService.save(withdrawal, authentication);
        return "redirect:/investor/withdrawals";
    }

    @GetMapping("investments")
    String investments(Model model, Authentication authentication){
        model.addAttribute("investments", investorInvestmentService.getCurrentUserInvestments(authentication));
        model.addAttribute("investor", investorService.getInvestorFromAuthentication(authentication));
        return "investor/investments";
    }

    @GetMapping("invest")
    String invest(Model model, CsrfToken csrfToken, Authentication authentication){
        model.addAttribute("investorInvestment", new InvestorInvestment());
        model.addAttribute("investments", investmentService.getAll());
        model.addAttribute("investor", investorService.getInvestorFromAuthentication(authentication));
        model.addAttribute("csrfToken", csrfToken);
        return "investor/invest";
    }

    @PostMapping("invest")
    String doInvest(@ModelAttribute InvestorInvestment investorInvestment, Authentication authentication){

        investorInvestmentService.save(investorInvestment, authentication);
        return "redirect:/investor/investments";
    }

    @GetMapping("profile")
    String profile(Model model, Authentication authentication, HttpServletRequest request){
        model.addAttribute("investor", investorService.getInvestorFromAuthentication(authentication));
        model.addAttribute("baseUrl", WebUtils.getBaseUrl(request));
        return "investor/profile";
    }
}
