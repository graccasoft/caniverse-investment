package io.caniverse.investment.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    private static final String ORIGINAL_ADMIN_SESSION = "originalAdmin";
    public void saveOriginalAdmin(HttpSession session, String adminUsername) {
        session.setAttribute(ORIGINAL_ADMIN_SESSION, adminUsername);
    }

    public String getOriginalAdmin(HttpSession session) {
        return (String) session.getAttribute(ORIGINAL_ADMIN_SESSION);
    }

    public void clearOriginalAdmin(HttpSession session) {
        session.removeAttribute(ORIGINAL_ADMIN_SESSION);
    }

}
