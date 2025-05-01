package com.eggcoach.core.common.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountURI {

	DEFAULT_LOGIN_REQUEST_URL("/login"),
	DEFAULT_LOGOUT_REQUEST_URL("/logout");

	private final String uri;
}
