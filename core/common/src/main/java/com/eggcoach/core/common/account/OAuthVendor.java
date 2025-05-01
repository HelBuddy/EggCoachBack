package com.eggcoach.core.common.account;

import lombok.Getter;

@Getter
public enum OAuthVendor {
	GOOGLE("google", "구글"),
	NAVER("naver","네이버"),
	KAKAO("kakao","카카오"),
	DEFAULT("default","기본");

	private final String code;

	private final String description;

	OAuthVendor(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public static OAuthVendor fromCode(String code) {
		for (OAuthVendor vendor : OAuthVendor.values()) {
			if (vendor.getOAuthVendor().equals(code)) {
				return vendor;
			}
		}
		throw new IllegalArgumentException(AccountErrorCode.INVALID_OAUTH2_TYPE_CODE.getCode());
	}

	public String getOAuthVendor() {
		return code;
	}
}
