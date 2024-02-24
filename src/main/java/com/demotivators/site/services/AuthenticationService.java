package com.demotivators.site.services;

import com.demotivators.site.configuration.SecureProperties;
import com.demotivators.site.models.ApiKeyAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationService {

    private final SecureProperties secureProperties;

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(secureProperties.getAuth_token_header_name());
        if (apiKey == null || !apiKey.equals(secureProperties.getAuth_token())) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
