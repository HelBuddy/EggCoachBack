package com.eggcoach.core.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.core.common.account.UserType;
import com.eggcoach.core.domain.account.model.User;
import com.eggcoach.core.domain.account.dto.SignUpDto;
import com.eggcoach.core.domain.security.adapter.UserServicePort;
import com.eggcoach.core.security.model.ProviderUser;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OauthUserService {

	@Autowired
	private UserServicePort userServicePort;

	public void signUpUser(String registrationId, ProviderUser providerUser) {
		userServicePort.signUp(SignUpDto.builder()
			.address(null)
			.profile(providerUser.getPicture())
			.password(providerUser.getPassword())
			.userEmail(providerUser.getEmail())
			.userType(UserType.SOCIAL.getCode())
			.socialProvider(OAuthVendor.fromCode(registrationId).getOAuthVendor())
			.userName(providerUser.getFirstName() == null ? providerUser.getUsername() :
				providerUser.getFirstName() + providerUser.getLastName())
			.build()
		);
	}

	public User getUserByUserEmail(String userEmail) {
		return userServicePort.findByUserEmail(userEmail);
	}
}
