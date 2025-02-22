package io.caniverse.investment.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class WebUtils {
    public static String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName()  + request.getContextPath();
    }

    public static HttpSession getSession() {
        var attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        var request = attr.getRequest();
        return request.getSession(false);
    }
    public static boolean isAdminLoggedIn() {
        var role = (String)getSession().getAttribute("originalAdmin");
        return role != null;
    }

    public static String formatDate(Instant instant){
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return instant.atZone(ZoneId.of("Africa/Harare")).format(formatter);
    }
}
