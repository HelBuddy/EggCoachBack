package com.eggcoach.core.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

import com.eggcoach.core.security.converter.ProviderUserConverter;
import com.eggcoach.core.security.converter.ProviderUserRequest;
import com.eggcoach.core.security.model.ProviderUser;

@Service
public class AbstractOAuth2UserService {

	@Autowired
	private OauthUserService oauthUserService;

	@Autowired
	private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

	protected ProviderUser providerUser(ProviderUserRequest providerUserRequest) {

		return providerUserConverter.converter(providerUserRequest);
	}

	/**
	 * 가입 안된 유저는 신규 가입
	 * @param providerUser - 각 벤더에 해당 되며 인가 받은 정보를 갖고 있다. ( 커스텀으로 만든 객체 )
	 * @param userRequest - 스프링 시큐리티 제공 OAuth2 객체
	 */
	protected void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {

		if (oauthUserService.getUserByUserEmail(providerUser.getEmail()) == null) {
			oauthUserService.signUpUser(userRequest.getClientRegistration().getRegistrationId(), providerUser);
		}
	}
}
