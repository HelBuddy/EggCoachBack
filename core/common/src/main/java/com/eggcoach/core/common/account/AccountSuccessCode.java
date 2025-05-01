package com.eggcoach.core.common.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountSuccessCode {

	SIGNUP_SUCCESS_CODE("SIGNUP_SUCCESS_CODE", "가입에 성공했습니다"),

	LOGIN_SUCCESS_CODE("LOGIN_SUCCESS_CODE", "로그인에 성공했습니다");

	private final String code;

	private final String message;

}
