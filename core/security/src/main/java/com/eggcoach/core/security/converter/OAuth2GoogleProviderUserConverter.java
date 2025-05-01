package com.eggcoach.core.security.converter;

import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.core.security.model.GoogleUser;
import com.eggcoach.core.security.model.ProviderUser;
import com.eggcoach.core.security.utils.OAuth2Utils;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

	@Override
	public ProviderUser converter(ProviderUserRequest providerUserRequest) {

		if (!OAuthVendor.GOOGLE.getOAuthVendor().equals(providerUserRequest.clientRegistration().getRegistrationId())) {
			return null;
		}
		return new GoogleUser(
			OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
			providerUserRequest.oAuth2User(),
			providerUserRequest.clientRegistration());
	}

}