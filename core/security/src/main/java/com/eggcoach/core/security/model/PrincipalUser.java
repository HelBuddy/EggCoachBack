package com.eggcoach.core.security.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public record PrincipalUser(ProviderUser providerUser) implements UserDetails, OAuth2User {

	@Override
	public String getName() {
		return providerUser.getUsername();
	}

	@Override
	public Map<String, Object> getAttributes() {
		return providerUser.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return providerUser.getAuthorities();
	}

	@Override
	public String getPassword() {
		return providerUser.getPassword();
	}

	@Override
	public String getUsername() {
		return providerUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
