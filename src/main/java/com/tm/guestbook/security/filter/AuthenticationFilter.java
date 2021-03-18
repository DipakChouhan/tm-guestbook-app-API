package com.tm.guestbook.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.guestbook.security.constant.SecurityConstants;
import com.tm.guestbook.security.model.LoginModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = null;
        try {
            LoginModel loginModel = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);

            LOGGER.info("Email: {}", loginModel.getEmail());

            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword(), new ArrayList<>()));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userEmail = ((User) authResult.getPrincipal()).getUsername();

        String token = JWT.create().withSubject(userEmail)
                .withExpiresAt(Date.from(LocalDateTime.now()
                        .plusDays(SecurityConstants.EXPIRATION_DAYS)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        response.addHeader(SecurityConstants.TOKEN_PREFIX, token);
    }
}
