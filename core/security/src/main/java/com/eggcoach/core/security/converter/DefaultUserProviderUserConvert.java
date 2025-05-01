package com.eggcoach.core.security.converter;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.core.domain.account.model.User;
import com.eggcoach.core.security.model.DefaultUser;
import com.eggcoach.core.security.model.ProviderUser;

public class DefaultUserProviderUserConvert implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

	@Override
	public ProviderUser converter(ProviderUserRequest providerUserRequest) {

		if (providerUserRequest.user() == null) {
			return null;
		}

		User user = providerUserRequest.user();

		return new DefaultUser(
			user.getUserName(),
			user.getPassword(),
			user.getUserEmail(),
			user.getSocialProvider().getOAuthVendor(),
			List.of(new SimpleGrantedAuthority(user.getUserType().getCode()))
		);
	}
}
