package com.eggcoach.core.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Component
public class CorsConfig {

	@Autowired
	private CorsSetting corsSetting;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfigVO corsConfigVO = corsSetting.corsConfigSetting();
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(corsConfigVO.getAllowedOrigins()));
		configuration.setAllowedMethods(Arrays.asList(corsConfigVO.getAllowedMethods()));
		configuration.setAllowedHeaders(Arrays.asList(corsConfigVO.getAllowedHeaders()));
		configuration.setAllowCredentials(corsConfigVO.isAllowCredentials());

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(corsConfigVO.getRegisterUrlBasedCorsConfig(), configuration);
		return source;
	}
}
