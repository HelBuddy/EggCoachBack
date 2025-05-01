package com.eggcoach.core.security.handler;

import static com.eggcoach.core.common.account.AccountErrorCode.*;

import java.io.IOException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.eggcoach.core.common.response.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

public class CustomSessionExpiredStrategy implements SessionInformationExpiredStrategy {
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		HttpServletResponse response = event.getResponse();

		response.setContentType("application/json;charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(JsonResponse.of(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED), DUPLICATE_LOGIN_LOGOUT.getMessage()));
		response.getWriter().write(json);
		response.flushBuffer();
	}
}