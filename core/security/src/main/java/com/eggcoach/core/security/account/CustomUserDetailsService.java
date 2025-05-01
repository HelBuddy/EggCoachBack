package com.eggcoach.core.security.account;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.security.adapter.UserServicePort;
import com.eggcoach.core.security.converter.ProviderUserRequest;
import com.eggcoach.core.security.model.PrincipalUser;
import com.eggcoach.core.security.model.ProviderUser;
import com.eggcoach.core.security.oauth.AbstractOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService extends AbstractOAuth2UserService implements UserDetailsService {

	private final UserServicePort userServicePort;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.eggcoach.core.domain.account.model.User user = userServicePort.findByUserEmail(username);

		ProviderUserRequest providerUserRequest = new ProviderUserRequest(null, null, user);

		ProviderUser providerUser = providerUser(providerUserRequest);

		// 임시 권한 배열
		return new PrincipalUser(providerUser);
	}
}
