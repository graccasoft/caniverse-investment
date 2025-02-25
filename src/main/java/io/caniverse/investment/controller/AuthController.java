package io.caniverse.investment.controller;

import io.caniverse.investment.model.dto.ConfirmOtpDto;
import io.caniverse.investment.model.dto.RegisterDto;
import io.caniverse.investment.model.dto.ResetPasswordDto;
import io.caniverse.investment.service.AuthService;
import io.caniverse.investment.service.OtpService;
import io.caniverse.investment.service.ValidationException;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;

    public AuthController(AuthService authService, OtpService otpService) {
        this.authService = authService;
        this.otpService = otpService;
    }

    @GetMapping({"login",""})
    String login(Model model,
                 CsrfToken csrfToken,
                 @RequestParam(name = "error", required = false) Integer error,
                 @RequestParam(name = "registered", required = false) Integer registered,
                 @RequestParam(name = "reset", required = false) Integer reset){
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("error", error != null  );
        model.addAttribute("registered", registered != null  );
        model.addAttribute("reset", reset != null  );
        return "login";
    }


    @GetMapping("register")
    String register(
            CsrfToken csrfToken, Model model, @RequestParam(value = "ref", required = false) Long ref){
        model.addAttribute("csrfToken",csrfToken);
        model.addAttribute("referrerId",ref);
        return "register";
    }

    @PostMapping("register")
    String doRegister(@ModelAttribute RegisterDto registerDto, Model model){
        try {
            authService.register(registerDto);
            return "redirect:/login?registered=1";
        }catch(ValidationException ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("url", "/register");
            return "error";
        }
    }

    @GetMapping("reset-password")
    String resetPassword(CsrfToken csrfToken,
                         Model model,
                         @RequestParam(name = "error", required = false) Integer error,
                         @RequestParam(name = "reset", required = false) Integer reset
                         ){
        model.addAttribute("csrfToken",csrfToken);
        model.addAttribute("error",error != null);
        model.addAttribute("reset",reset != null);
        return "reset-password";
    }

    @PostMapping("reset-password")
    String doResetPassword(@ModelAttribute ResetPasswordDto resetPasswordDto, Model model){
        try{
            otpService.sendOtpEmail(resetPasswordDto.email());
            return "redirect:/reset-password?reset=1";
        }catch(ValidationException ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("url", "/reset-password");
            return "error";
        }
    }

    @GetMapping("change-password")
    String changePassword(CsrfToken csrfToken,
                          Model model,
                          @RequestParam(name = "error", required = false) Integer error,
                          @RequestParam(name = "reset", required = false) Integer reset,
                          @RequestParam(name = "username", required = false) String username,
                          @RequestParam(name = "otp", required = false) String otp){
        model.addAttribute("csrfToken",csrfToken);
        model.addAttribute("error",error != null);
        model.addAttribute("reset",reset != null);
        model.addAttribute("username",username);
        model.addAttribute("otp",otp);
        return "change-password";
    }

    @PostMapping("change-password")
    String doChangePassword(@ModelAttribute ConfirmOtpDto confirmOtpDto, Model model){
        try{
            authService.changePassword(confirmOtpDto);
            return "redirect:/login?reset=1";
        }catch(ValidationException ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("url", "/change-password");
            return "error";
        }
    }
}
