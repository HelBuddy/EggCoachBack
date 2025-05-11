package com.eggcoach.core.security.filter;

import static com.eggcoach.core.common.account.AccountErrorCode.*;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.eggcoach.core.common.account.AccountURI;
import com.eggcoach.core.common.response.JsonResponse;
import com.eggcoach.core.domain.account.dto.LoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private static final String HTTP_METHOD = "POST";
	private static final String CONTENT_TYPE = "application/json";
	private final ObjectMapper objectMapper;
	private SessionAuthenticationStrategy sessionAuthenticationStrategy;

	public CustomUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper,
		AuthenticationManager authenticationManager,SessionAuthenticationStrategy sessionAuthenticationStrategy) {
		super(new AntPathRequestMatcher(AccountURI.DEFAULT_LOGIN_REQUEST_URL.getUri(), HTTP_METHOD));
		this.objectMapper = objectMapper;
		this.sessionAuthenticationStrategy = sessionAuthenticationStrategy;
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException, IOException {

		if(request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE)) {
			throw new AuthenticationServiceException("Content Type not supported");
		}

		LoginRequestDto loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);
		if (StringUtils.isEmpty(loginRequest.getUserEmail()) || StringUtils.isEmpty(loginRequest.getPassword())) {
			throw new AuthenticationServiceException("Username or Password not provided");
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
			loginRequest.getUserEmail(), loginRequest.getPassword());

		Authentication authentication = this.getAuthenticationManager().authenticate(token);

		return authentication;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		FilterChain chain, Authentication authResult) throws IOException, ServletException {
		// 인증 객체 super로 꼭 넘기기

		sessionAuthenticationStrategy.onAuthentication(authResult, request, response);

		super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException failed) throws IOException {

		response.setContentType("application/json;charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(
			JsonResponse.of(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED), FAIL_LOGIN.getMessage()));
		response.getWriter().write(json);
		response.flushBuffer();
	}
}