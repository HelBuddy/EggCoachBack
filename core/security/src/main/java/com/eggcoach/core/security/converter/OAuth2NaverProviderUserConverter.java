package com.eggcoach.core.security.converter;

import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.core.security.model.NaverUser;
import com.eggcoach.core.security.model.ProviderUser;
import com.eggcoach.core.security.utils.OAuth2Utils;

public class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

	@Override
	public ProviderUser converter(ProviderUserRequest providerUserRequest) {

		if (!(OAuthVendor.NAVER.getOAuthVendor().equals(providerUserRequest.clientRegistration().getRegistrationId()))) {
			return null;
		}
		return new NaverUser(
			OAuth2Utils.getSubAttributes(providerUserRequest.oAuth2User(), "response"),
			providerUserRequest.oAuth2User(),
			providerUserRequest.clientRegistration());
	}

}
