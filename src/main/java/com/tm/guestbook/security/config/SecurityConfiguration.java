package com.tm.guestbook.security.config;

import com.tm.guestbook.security.constant.SecurityConstants;
import com.tm.guestbook.security.filter.AuthenticationFilter;
import com.tm.guestbook.security.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = SecurityConstants.BEAN_USER_DETAILS_SERVICE_IMPL)
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(
                        SecurityConstants.PATH_SECURITY + SecurityConstants.PATH_USER_DETAILS + SecurityConstants.PATH_REGISTER,
                        SecurityConstants.PATH_SWAGGER_API_DOCS,
                        SecurityConstants.PATH_SECURITY + SecurityConstants.PATH_SIGNIN,
                        SecurityConstants.PATH_SWAGGER_UI,
                        SecurityConstants.PATH_SWAGGER_RESOURCES,
                        SecurityConstants.PATH_SWAGGER_WEBJARS)
                .permitAll()
                .anyRequest().authenticated()
        .and()
                    .addFilter(getAuthenticationFilter())
                    .addFilter(getAuthorizationFilter())
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean(name = SecurityConstants.BEAN_PASSWORD_ENCODER)
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        return new AuthenticationFilter(authenticationManager());
    }

    public AuthorizationFilter getAuthorizationFilter() throws Exception {
        return new AuthorizationFilter(authenticationManager());
    }
}