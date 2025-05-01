package com.eggcoach.core.common.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountErrorCode {

	DUPLICATE_EMAIL("DUPLICATE_EMAIL", "이메일이 이미 존재합니다."),
	WRONG_PASSWORD("WRONG_PASSWORD", "비밀번호가 올바르지 않습니다."),
	INVALID_USER_TYPE_CODE("INVALID_USER_TYPE_CODE", "유저타입코드가 올바르지 않습니다."),
	INVALID_OAUTH2_TYPE_CODE("INVALID_OAUTH2_TYPE_CODE", "소셜코드가 올바르지 않습니다."),
	INVALID_USER_STATUS_CODE("INVALID_USER_STATUS_CODE", "유저상태코드가 올바르지 않습니다."),
	DUPLICATE_LOGIN_LOGOUT("DUPLICATE_LOGIN_LOGOUT", "다른 기기에서 로그인 되어 로그아웃 되었습니다."),
	FAIL_LOGIN("FAIL_LOGIN", "로그인에 실패했습니다.");

	private final String code;

	private final String message;

}
