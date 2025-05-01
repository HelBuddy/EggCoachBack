package com.eggcoach.infrastructure.account.service;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.account.service.UserService;
import com.eggcoach.infrastructure.account.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public boolean isDuplicatedUserEmail(String email) {

		return userRepository.existsByUserEmail(email);
	}

}
