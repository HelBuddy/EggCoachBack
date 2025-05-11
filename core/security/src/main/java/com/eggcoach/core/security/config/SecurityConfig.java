package com.eggcoach.core.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.cors.CorsConfigurationSource;

import com.eggcoach.core.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.eggcoach.core.security.handler.CustomJsonAuthenticationSuccessHandler;
import com.eggcoach.core.security.handler.CustomOAuth2SuccessHandler;
import com.eggcoach.core.security.handler.CustomSessionExpiredStrategy;
import com.eggcoach.core.security.oauth.CustomOAuth2UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@Import({CorsConfig.class, CustomOAuth2UserService.class})
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Value("${address.frontserver}")
	private String frontServer;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,
		@Qualifier("corsConfigurationSource")
		CorsConfigurationSource corsConfigurationSource,
		CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter,
		SessionRegistry sessionRegistry) throws Exception {

		http
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable);

		http
			.cors(cors -> cors
				.configurationSource(corsConfigurationSource))
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/**").permitAll()
				.anyRequest().authenticated()
			);

		http
			.securityContext(context -> context
				.securityContextRepository(new HttpSessionSecurityContextRepository())
				.requireExplicitSave(false))
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.sessionFixation().newSession()
				.maximumSessions(1)
				.maxSessionsPreventsLogin(false)
				.expiredSessionStrategy(new CustomSessionExpiredStrategy())
			);

		http
			.addFilterAt(customUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessHandler((request, response, authentication) -> {
					response.setContentType("application/json");
					response.getWriter().write("{\"success\": true}");
				}));

		http
			.oauth2Login(oauth2 -> oauth2
				.successHandler(new CustomOAuth2SuccessHandler(sessionRegistry, frontServer))
				.userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
					.userService(customOAuth2UserService))
				.permitAll());

		return http.build();
	}

	// 세션 필터
	@Bean
	private static CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter(
		AuthenticationConfiguration authenticationConfiguration,
		SessionAuthenticationStrategy sessionAuthenticationStrategy,
		SessionRegistry sessionRegistry) throws Exception {
		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter(
			new ObjectMapper(), authenticationConfiguration.getAuthenticationManager(), sessionAuthenticationStrategy);
		filter.setAuthenticationSuccessHandler(new CustomJsonAuthenticationSuccessHandler(sessionRegistry));
		return filter;
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new org.springframework.security.core.session.SessionRegistryImpl();
	}

	@Bean
	public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(sessionRegistry());
	}


}