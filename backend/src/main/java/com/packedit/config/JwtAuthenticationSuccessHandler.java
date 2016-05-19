package com.packedit.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest arg0, final HttpServletResponse arg1, final Authentication arg2)
            throws IOException, ServletException {
        // We do not need to do anything extra on REST authentication success, because there is no page to redirect to
    }
}