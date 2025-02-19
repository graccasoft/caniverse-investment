package io.caniverse.investment.controller.admin;

import io.caniverse.investment.model.dto.PlaceInvestmentDto;
import io.caniverse.investment.model.dto.WithdrawDto;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.service.InvestmentService;
import io.caniverse.investment.service.InvestorInvestmentService;
import io.caniverse.investment.service.InvestorService;
import io.caniverse.investment.service.WithdrawalService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/investors")
public class InvestorsController {
    private final InvestorInvestmentService investorInvestmentService;
    private final InvestorService investorService;
    private final InvestmentService investmentService;
    private final WithdrawalService withdrawalService;

    public InvestorsController(InvestorInvestmentService investorInvestmentService, InvestorService investorService, InvestmentService investmentService, WithdrawalService withdrawalService) {
        this.investorInvestmentService = investorInvestmentService;
        this.investorService = investorService;
        this.investmentService = investmentService;
        this.withdrawalService = withdrawalService;
    }

    @GetMapping
    String investors(Model model, CsrfToken csrfToken) {
        Investor investor = new Investor();
        investor.setName("");
        model.addAttribute("investors", investorService.findInvestors(investor));
        model.addAttribute("investorQuery", investor);
        model.addAttribute("csrfToken", csrfToken);
        return "admin/investors";
    }

    @PostMapping
    String searchInvestors(@ModelAttribute Investor investor, Model model, CsrfToken csrfToken) {
        model.addAttribute("investors", investorService.findInvestors(investor));
        model.addAttribute("investorQuery", investor);
        model.addAttribute("csrfToken", csrfToken);
        return "admin/investors";
    }

    @GetMapping("{id}/invest")
    String investForInvestor(@PathVariable("id") Long id, Model model, CsrfToken csrfToken) {
        model.addAttribute("investor", investorService.getInvestor(id));
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("investments", investmentService.getAll());
        return "admin/invest";
    }


    @PostMapping("{id}/invest")
    String doInvestForInvestor(@ModelAttribute PlaceInvestmentDto placeInvestmentDto, @PathVariable Long id) {
        investorInvestmentService.save(placeInvestmentDto, id);
        return "redirect:/admin/investors";
    }

    @GetMapping("{id}/withdraw")
    String withdrawForInvestor(@PathVariable Long id, Model model, CsrfToken csrfToken){
        model.addAttribute("investor", investorService.getInvestor(id));
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("investments", investorInvestmentService.getInvestorInvestments(id));
        return "admin/withdraw";
    }
    @PostMapping("{id}/withdraw")
    String doWithdrawForInvestor(@ModelAttribute WithdrawDto withdrawDto, @PathVariable Long id){
        withdrawalService.save(withdrawDto, id);
        return "redirect:/admin/investors";
    }
}
