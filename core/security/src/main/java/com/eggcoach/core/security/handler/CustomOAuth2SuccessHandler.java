package com.eggcoach.core.security.handler;

import static com.eggcoach.core.common.account.AccountErrorCode.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.eggcoach.core.common.account.AccountSuccessCode;
import com.eggcoach.core.common.response.JsonResponse;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.security.converter.CustomAuthConverter;
import com.eggcoach.core.security.model.PrincipalUser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	private final SessionRegistry sessionRegistry;

	private String frontServer;

	public CustomOAuth2SuccessHandler(SessionRegistry sessionRegistry, String frontServer) {
		this.sessionRegistry = sessionRegistry;
		this.frontServer = frontServer;
	}


	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {

		var token = CustomAuthConverter.from(authentication).toCustomPrincipalToken();
		CustomPrincipal customPrincipal = (CustomPrincipal) token.getPrincipal();

		// 이전 세션 만료 전략
		CustomSessionExpiredHandler.from(sessionRegistry).sessionExpire(request, customPrincipal);

		CustomAuthConverter.from(authentication).setSecurityContext(token);
		CustomAuthConverter.from(authentication).setSessionAttribute(request);

		response.sendRedirect(frontServer);
	}


}