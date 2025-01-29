package io.caniverse.investment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/investor")
public class InvestorController {

    @GetMapping
    String dashboard(){
        return "investor/dashboard";
    }

    @GetMapping("withdrawals")
    String withdrawals(){
        return "investor/withdrawals";
    }

    @GetMapping("withdraw")
    String withdraw(){
        return "investor/withdraw";
    }

    @GetMapping("investments")
    String investments(){
        return "investor/investments";
    }

    @GetMapping("invest")
    String invest(){
        return "investor/invest";
    }
}
