package com.eggcoach.core.domain.security.adapter;

import com.eggcoach.core.domain.account.model.User;
import com.eggcoach.core.domain.account.dto.SignUpDTO;

public interface UserServicePort {

	// 시큐리티에 security context 담을 정보
	// 각 모듈간에 의존성을 최소화 하기 위함
	User findByUserEmail(String userEmail);

	User signUp(SignUpDTO signUpDTO);
}
