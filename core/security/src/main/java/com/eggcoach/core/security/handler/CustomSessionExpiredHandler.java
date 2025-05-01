package com.eggcoach.core.security.handler;

import java.util.List;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import com.eggcoach.core.domain.security.vo.CustomPrincipal;

import jakarta.servlet.http.HttpServletRequest;

public class CustomSessionExpiredHandler {

	private final SessionRegistry sessionRegistry;

	private CustomSessionExpiredHandler(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public static CustomSessionExpiredHandler from(SessionRegistry sessionRegistry) {
		return new CustomSessionExpiredHandler(sessionRegistry);
	}

	public void sessionExpire(HttpServletRequest request, CustomPrincipal customPrincipal) {
		sessionRegistry.registerNewSession(request.getSession().getId(), customPrincipal.getEmail());
		List<SessionInformation> sessions = sessionRegistry.getAllSessions(customPrincipal.getEmail(), false);
		for (SessionInformation session : sessions) {
			// 현재 세션은 만료 시키지 않도록 체크
			if (!session.getSessionId().equals(request.getSession().getId())) {
				session.expireNow();
			}
		}
	}
}
