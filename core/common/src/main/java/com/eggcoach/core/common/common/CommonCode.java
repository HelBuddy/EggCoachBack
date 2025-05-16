package com.eggcoach.core.common.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonCode {

	COMMON_YES_CODE("COMMON_YES_CODE", "존재합니다"),

	COMMON_NO_CODE("COMMON_NO_CODE", "존재하지않습니다"),

	COMMON_SUCCESS_CODE("COMMON_SUCCESS_CODE", "성공했습니다"),

	COMMON_FAIL_CODE("COMMON_FAIL_CODE", "실패했습니다.");

	private final String code;

	private final String message;
}
