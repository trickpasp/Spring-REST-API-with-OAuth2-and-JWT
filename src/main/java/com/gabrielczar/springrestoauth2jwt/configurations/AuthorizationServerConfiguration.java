package com.gabrielczar.springrestoauth2jwt.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private final String GRANT_TYPE_AUTH_CODE = "authorization_code";
    private final String GRANT_TYPE_REFRESH = "refresh_token";
    private final String GRANT_TYPE_PASSWORD = "password";
    private final String SCOPE_WRITE = "write";
    private final String SCOPE_READ = "read";

    private final AuthenticationManager authenticationManager;

    @Value("${security.oauth2.resource.jwt.signing-key:mw5BG4UXKAZKznRbRd3tHXf7hfjKfPY1}")
    private String signingKey;

    @Value("${security.oauth2.resource.id:DA2D532D1BEjwtresourceid}")
    private String resourceId;

    @Value("${security.oauth2.client.client-id:spring-rest-oauth2-jwt}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret:B6813193F1D7EC8BF5B40}")
    private String clientSecret;

    @Value("${security.oauth2.client.access-token-validity-seconds:604800}")
    private Integer accessTokenValidity;

    @Value("${security.oauth2.client.refresh-token-validity-seconds:2592000}")
    private Integer refreshTokenValidity;

    @Autowired
    public AuthorizationServerConfiguration(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, GRANT_TYPE_REFRESH, GRANT_TYPE_AUTH_CODE)
                .scopes(SCOPE_READ, SCOPE_WRITE)
                .resourceIds(resourceId)
                .refreshTokenValiditySeconds(accessTokenValidity)
                .accessTokenValiditySeconds(refreshTokenValidity);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        return defaultTokenServices;
    }

}
