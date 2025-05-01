package com.eggcoach.infrastructure.security;

import org.springframework.stereotype.Repository;

import com.eggcoach.core.domain.account.model.User;
import com.eggcoach.core.domain.account.dto.SignUpDTO;
import com.eggcoach.core.domain.security.adapter.UserPasswordPort;
import com.eggcoach.core.domain.security.adapter.UserServicePort;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserServicePortImpl implements UserServicePort {

	private final UserRepository userRepository;

	private final UserPasswordPort passwordUtil;

	@Override
	public User findByUserEmail(String email) {
		return userRepository.findByUserEmail(email)
			.map(UserEntity::user) // UserEntity를 User로 변환
			.orElse(null);
	}

	@Override
	public User signUp(SignUpDTO signUpDTO) {
		UserEntity userEntity = new UserEntity().signUp(signUpDTO);
		userEntity.encodePassword(passwordUtil.encodedPassword(userEntity.getPassword()));

		return userRepository.save(userEntity).user();
	}
}
