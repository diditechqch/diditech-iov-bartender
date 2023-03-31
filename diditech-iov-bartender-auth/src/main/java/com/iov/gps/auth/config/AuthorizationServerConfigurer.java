package com.iov.gps.auth.config;

import java.security.KeyPair;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * oauth2 授权服务器配置
 * @author hefan
 * @date 2020/8/18 10:22
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final @NonNull AuthenticationManager authenticationManager;
    private final @NonNull ClientDetails clientDetails;
    private final @NonNull RedisConnectionFactory redisConnectionFactory;
    private final @NonNull KeyPairConfig keyPairConfig;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        configClient(clients);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(redisTokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyPairConfig.getFilename()),
                keyPairConfig.getPassword().toCharArray());
        return keyStoreKeyFactory.getKeyPair(keyPairConfig.getAlias(), keyPairConfig.getPassword().toCharArray());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .allowFormAuthenticationForClients();
    }

    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    private void configClient(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        for (BaseClientDetails client : clientDetails.getClient()) {
            ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder clientBuilder =
                    builder.withClient(client.getClientId());
            clientBuilder
                    .secret(client.getClientSecret())
                    .resourceIds(client.getResourceIds().toArray(new String[0]))
                    .authorizedGrantTypes(client.getAuthorizedGrantTypes().toArray(new String[0]))
                    .authorities(
                            AuthorityUtils.authorityListToSet(client.getAuthorities())
                                    .toArray(new String[0]))
                    .scopes(client.getScope().toArray(new String[0]));
            if (client.getAutoApproveScopes() != null) {
                clientBuilder.autoApprove(
                        client.getAutoApproveScopes().toArray(new String[0]));
            }
            if (client.getAccessTokenValiditySeconds() != null) {
                clientBuilder.accessTokenValiditySeconds(
                        client.getAccessTokenValiditySeconds());
            }
            if (client.getRefreshTokenValiditySeconds() != null) {
                clientBuilder.refreshTokenValiditySeconds(
                        client.getRefreshTokenValiditySeconds());
            }
            if (client.getRegisteredRedirectUri() != null) {
                clientBuilder.redirectUris(
                        client.getRegisteredRedirectUri().toArray(new String[0]));
            }
        }
    }
}
