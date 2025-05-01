package com.eggcoach.core.common.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

	// 휴면 상태
	DORMANT("D", "휴면 상태"),

	// 정상 상태
	ACTIVE("A", "정상 상태"),

	// 신고당해 정지된 상태
	SUSPENDED("S", "신고로 정지된 상태");

	private final String code;

	private final String description;

	public static UserStatus fromCode(String code) {
		for (UserStatus value : UserStatus.values()) {
			if(value.getCode().equals(code)) {
				return value;
			}
		}
		throw new IllegalArgumentException(AccountErrorCode.INVALID_USER_STATUS_CODE.getCode());
	}

}
