package io.caniverse.investment.controller;

import io.caniverse.investment.model.dto.RegisterDto;
import io.caniverse.investment.service.AuthService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"login",""})
    String login(Model model,
                 CsrfToken csrfToken,
                 @RequestParam(name = "error", required = false) Integer error,
                 @RequestParam(name = "registered", required = false) Integer registered){
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("error", error != null  );
        model.addAttribute("registered", registered != null  );
        return "login";
    }


    @GetMapping("register")
    String register(CsrfToken csrfToken, Model model, @RequestParam(value = "ref", required = false) Long ref){
        model.addAttribute("csrfToken",csrfToken);
        model.addAttribute("referrerId",ref);
        return "register";
    }

    @PostMapping("register")
    String doRegister(@ModelAttribute RegisterDto registerDto){
        authService.register(registerDto);
        return "redirect:/login?registered=1";
    }
}
