package com.eggcoach.core.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.eggcoach.core.common.account.AccountSuccessCode;
import com.eggcoach.core.common.response.JsonResponse;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.security.converter.CustomAuthConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomJsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final SessionRegistry sessionRegistry;

	public CustomJsonAuthenticationSuccessHandler(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication)
		throws IOException, ServletException {

		var token = CustomAuthConverter.from(authentication).toCustomPrincipalToken();
		CustomPrincipal customPrincipal = (CustomPrincipal) token.getPrincipal();

		// 이전 세션 만료 전략
		CustomSessionExpiredHandler.from(sessionRegistry).sessionExpire(request, customPrincipal);

		CustomAuthConverter.from(authentication).setSecurityContext(token);
		CustomAuthConverter.from(authentication).setSessionAttribute(request);

		response.setContentType("application/json;charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(
			JsonResponse.of(String.valueOf(HttpServletResponse.SC_OK), AccountSuccessCode.LOGIN_SUCCESS_CODE.getMessage()));
		response.getWriter().write(json);
		response.flushBuffer();
	}
}
