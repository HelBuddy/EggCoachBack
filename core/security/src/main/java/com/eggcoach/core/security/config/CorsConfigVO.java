package com.eggcoach.core.security.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CorsConfigVO {

	private String[] allowedOrigins;

	private String[] allowedMethods;

	private String[] allowedHeaders;

	private boolean allowCredentials;

	private String registerUrlBasedCorsConfig;
}
