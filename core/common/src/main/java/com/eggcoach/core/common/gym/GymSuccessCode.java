package com.eggcoach.core.common.gym;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GymSuccessCode {

	SIGNUP_SUCCESS_CODE("SIGNUP_SUCCESS_CODE", "등록에 성공했습니다"),

	GET_BOUNDS_LIST_SUCCESS_CODE("GET_BOUNDS_LIST_SUCCESS_CODE", "헬스장 목록 조회에 성공했습니다");

	private final String code;

	private final String message;
}
