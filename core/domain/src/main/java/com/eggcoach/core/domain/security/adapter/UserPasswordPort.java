package com.eggcoach.core.domain.security.adapter;

public interface UserPasswordPort {

	String encodedPassword(String password);

	Boolean isMatches(String inputPassword, String dbPassword);
}
