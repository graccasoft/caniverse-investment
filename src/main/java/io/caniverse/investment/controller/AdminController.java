package io.caniverse.investment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping({"","investments"})
    String investments(){
        return "admin/investments";
    }

    @GetMapping("investments/{id}")
    String investment(@PathVariable Long id){
        return "admin/investment";
    }

    @GetMapping("withdrawals")
    String withdrawals(){
        return "admin/withdrawals";
    }

    @GetMapping("withdrawals/{id}")
    String withdrawal(@PathVariable Long id){
        return "admin/withdrawal";
    }

}
