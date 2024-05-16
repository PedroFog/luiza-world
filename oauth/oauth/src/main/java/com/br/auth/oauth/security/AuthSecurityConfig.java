package com.br.auth.oauth.security;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfig {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		return http.formLogin(Customizer.withDefaults()).build();
	}

	@Bean
	SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated();
		return http.formLogin(Customizer.withDefaults()).build();
	}

	@Bean
	RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {

		RegisteredClient luizaClient = RegisteredClient.withId("1").clientId("luiza")
				.clientSecret(passwordEncoder.encode("pedro123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofMinutes(5)).build())
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build()).build();
		;

		return new InMemoryRegisteredClientRepository(Arrays.asList(luizaClient));
	}

	@Bean
	ProviderSettings providerSettings(AuthProperties authProperties) {
		return ProviderSettings.builder().issuer(authProperties.getProviderUri()).build();
	}

	@Bean
	JWKSet jwkSet(AuthProperties authProperties) throws Exception {
		final var jksProperties = authProperties.getJks();
		final String jksPath = jksProperties.getPath();
		final InputStream inputStream = new ClassPathResource(jksPath).getInputStream();

		final KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(inputStream, jksProperties.getStorepass().toCharArray());

		RSAKey rsaKey = RSAKey.load(keyStore, jksProperties.getAlias(), jksProperties.getKeypass().toCharArray());

		return new JWKSet(rsaKey);
	}

	@Bean
	JWKSource<SecurityContext> jwkSource(JWKSet jwkSet) {
		return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
	}

	@Bean
	JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}

	@Bean
	@Order(1)
	public SecurityFilterChain jwksSecurityFilterChain(HttpSecurity http) throws Exception {
		http.requestMatchers(requestMatchers -> requestMatchers.antMatchers("/.well-known/jwks.json"))
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
				.csrf(AbstractHttpConfigurer::disable).formLogin(AbstractHttpConfigurer::disable)
				.httpBasic(AbstractHttpConfigurer::disable);
		return http.build();
	}

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults());
		return http.build();
	}

}
