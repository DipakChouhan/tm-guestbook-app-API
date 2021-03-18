package com.tm.guestbook.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ApiConfiguration implements WebMvcConfigurer {


    /**
     * This method updates the cors registry with user settings
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS").allowedHeaders("*").allowedOrigins("*");
    }

    /**
     * This method specifies the configuration for the file to be uploaded
     * @return CommonsMultipartResolver
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 10 MegaBytes
        multipartResolver.setMaxUploadSizePerFile(3048576L); //3 Megabytes Per File Limit
        return multipartResolver;
    }
}
