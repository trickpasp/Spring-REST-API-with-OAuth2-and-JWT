package com.gabrielczar.springrestoauth2jwt.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CORSConfiguration {

    @Value("${cors.allowedHeaders}")
    private List<String> allowedHeaders;

    @Value("${cors.allowedMethods}")
    private List<String> allowedMethods;

    @Value("${cors.allowedOrigin}")
    private String allowedOrigin;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(allowedOrigin);
        config.setAllowedMethods(allowedMethods);
        config.setAllowedHeaders(allowedHeaders);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
