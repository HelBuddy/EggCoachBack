package com.eggcoach.core.common.external;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressURI {

	KAKAO_MAP_REQUEST_URL("https://dapi.kakao.com/v2/local/search/address.json");

	private final String uri;

}
