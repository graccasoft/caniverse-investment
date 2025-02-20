package io.caniverse.investment.controller;

import io.caniverse.investment.config.CustomAuthenticationToken;
import io.caniverse.investment.config.SessionManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleSwitchController {
    private final SessionManager sessionManager;
    public RoleSwitchController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/switch-to-investor")
    public String switchToInvestor(@RequestParam(name = "u") String investorUsername, HttpSession session) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        // Save admin session before switching
        sessionManager.saveOriginalAdmin(session, currentAuth.getName());

        // Set investor authentication (simulate login as investor)
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication investorAuth = new CustomAuthenticationToken(investorUsername, "INVESTOR");
        securityContext.setAuthentication(investorAuth);

        return "redirect:/investor";  // Redirect to investor page
    }

    @GetMapping("/switch-back-to-admin")
    public String switchBackToAdmin(HttpSession session) {
        String adminUsername = sessionManager.getOriginalAdmin(session);

        if (adminUsername != null) {
            // Restore original admin authentication
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication adminAuth = new CustomAuthenticationToken(adminUsername, "ADMIN");
            securityContext.setAuthentication(adminAuth);

            // Clear session data
            sessionManager.clearOriginalAdmin(session);

            return "redirect:/admin";
        }

        return "redirect:/";
    }
}
