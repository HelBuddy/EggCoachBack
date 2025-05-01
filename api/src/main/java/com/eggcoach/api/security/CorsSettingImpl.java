package com.eggcoach.api.security;

import org.springframework.context.annotation.Configuration;

import com.eggcoach.core.security.config.CorsConfigVO;
import com.eggcoach.core.security.config.CorsSetting;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class CorsSettingImpl implements CorsSetting {

	@Value("${cors.allowed-origins}")
	private String allowedOrigin;

	@Override
	public CorsConfigVO corsConfigSetting() {
		return CorsConfigVO.builder()
			.allowedOrigins(new String[] {allowedOrigin})
			.allowedMethods(new String[] {"GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"})
			.allowedHeaders(new String[] {"*"})
			.allowCredentials(true)
			.registerUrlBasedCorsConfig("/**")
			.build();
	}
}
