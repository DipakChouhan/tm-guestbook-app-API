package com.tm.guestbook.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tm.guestbook.common.utility.ExecutionContext;
import com.tm.guestbook.common.utility.GuestBookSecurityContextHolder;
import com.tm.guestbook.security.constant.SecurityConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (null == token) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = performAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken performAuthentication(String token) {
        String username = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes())).build()
                .verify(token.replace(SecurityConstants.TOKEN_PREFIX + " ", "")).getSubject();
        ExecutionContext executionContext = new ExecutionContext();
        executionContext.setUserEmail(username);
        GuestBookSecurityContextHolder.setExecutionContext(executionContext);
        return username != null? new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()): null;
    }
}
