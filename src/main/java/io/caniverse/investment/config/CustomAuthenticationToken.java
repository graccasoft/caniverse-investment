package io.caniverse.investment.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public CustomAuthenticationToken(String username, String role) {
        super(username, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}
