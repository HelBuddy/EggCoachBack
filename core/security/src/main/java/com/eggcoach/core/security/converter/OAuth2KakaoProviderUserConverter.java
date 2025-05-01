package com.eggcoach.core.security.converter;

import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.core.security.model.KakaoUser;
import com.eggcoach.core.security.model.ProviderUser;
import com.eggcoach.core.security.utils.OAuth2Utils;

public class OAuth2KakaoProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
	@Override
	public ProviderUser converter(ProviderUserRequest providerUserRequest) {

		if (!OAuthVendor.KAKAO.getOAuthVendor().equals(providerUserRequest.clientRegistration().getRegistrationId())) {
			return null;
		}
		return new KakaoUser(
			OAuth2Utils.getOtherAttributes(providerUserRequest.oAuth2User(), "kakao_account", "profile"),
			providerUserRequest.oAuth2User(),
			providerUserRequest.clientRegistration());
	}
}
