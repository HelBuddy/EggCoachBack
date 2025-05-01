package com.eggcoach.core.security.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eggcoach.core.domain.security.adapter.UserPasswordPort;

@Configuration
public class UserPasswordPortsImpl implements UserPasswordPort {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public String encodedPassword(String password) {
		return passwordEncoder().encode(password);
	}

	@Override
	public Boolean isMatches(String inputPassword, String dbPassword) {
		return passwordEncoder().matches(inputPassword, dbPassword);
	}
}
