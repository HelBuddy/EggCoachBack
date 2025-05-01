package com.eggcoach.core.security.oauth;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.account.dto.SignUpDTO;
import com.eggcoach.core.domain.security.adapter.UserServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultUserService extends AbstractOAuth2UserService {

	private final UserServicePort userServicePort;

	public void signUpUser(SignUpDTO signUpDTO) {

		userServicePort.signUp(signUpDTO);
	}

}
