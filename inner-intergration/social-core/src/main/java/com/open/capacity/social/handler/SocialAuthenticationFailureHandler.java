package com.open.capacity.social.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.social.security.SocialAuthenticationRedirectException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("socialAuthenticationFailureHandler")
public class SocialAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof SocialAuthenticationRedirectException) {
            response.sendRedirect(((SocialAuthenticationRedirectException) exception).getRedirectUrl());
        } else {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
