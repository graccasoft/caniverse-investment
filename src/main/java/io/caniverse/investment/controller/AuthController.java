package io.caniverse.investment.controller;

import io.caniverse.investment.model.dto.RegisterDto;
import io.caniverse.investment.service.AuthService;
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

    @GetMapping("login")
    String login(Model model, CsrfToken csrfToken, @RequestParam(name = "error", required = false) Integer error){
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("error", error != null  );
        return "login";
    }


    @GetMapping("register")
    String register(CsrfToken csrfToken, Model model){
        model.addAttribute("csrfToken",csrfToken);
        return "register";
    }

    @PostMapping("register")
    String doRegister(@ModelAttribute RegisterDto registerDto){
        authService.register(registerDto);
        return "redirect:/register";
    }
}
