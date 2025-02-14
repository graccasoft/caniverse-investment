package io.caniverse.investment.utils;

import jakarta.servlet.http.HttpServletRequest;

public class WebUtils {
    public static String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
