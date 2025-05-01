package com.eggcoach.core.security.util;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionInfoPrinter {
	private final SessionRegistry sessionRegistry;

	public SessionInfoPrinter(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public void printSessionInformation() {
		List<Object> principals = sessionRegistry.getAllPrincipals();
		int sessionCount = 0;
		for (Object principal : principals) {
			List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
			sessionCount += sessions.size();
			for (SessionInformation session : sessions) {
				System.out.println("세션ID: " + session.getSessionId()
					+ ", 마지막 요청: " + session.getLastRequest()
					+ ", 만료여부: " + session.isExpired());
			}
		}
		System.out.println("현재 전체 활성 세션 개수: " + sessionCount);
	}
}