package com.eggcoach.core.common.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {

	CUSTOMER("C", "일반회원"),
	TRAINER("T", "트레이너"),
	ADMIN("A", "관리자"),
	SOCIAL("S", "소셜가입");

	private final String code;

	private final String description;

	public boolean isCustomer() {
		return this == CUSTOMER;
	}

	public boolean isTrainer() {
		return this == TRAINER;
	}

	public boolean isAdmin() {
		return this == ADMIN;
	}

	public static UserType fromCode(String code) {
		for (UserType status : UserType.values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		throw new IllegalArgumentException(AccountErrorCode.INVALID_USER_TYPE_CODE.getCode());
	}
}
