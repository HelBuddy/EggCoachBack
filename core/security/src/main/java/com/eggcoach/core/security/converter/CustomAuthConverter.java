package com.eggcoach.core.security.converter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.security.model.PrincipalUser;

import jakarta.servlet.http.HttpServletRequest;

public class CustomAuthConverter {

	private final Authentication authentication;

	private CustomAuthConverter(Authentication authentication) {
		this.authentication = authentication;
	}

	public static CustomAuthConverter from(Authentication authentication) {
		return new CustomAuthConverter(authentication);
	}

	public UsernamePasswordAuthenticationToken toCustomPrincipalToken() {

		PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();

		CustomPrincipal customPrincipal = new CustomPrincipal(
			principalUser.providerUser().getAuthorities().getFirst().getAuthority(),
			principalUser.providerUser().getFirstName() == null ? principalUser.getUsername() :
				principalUser.providerUser().getFirstName() + principalUser.providerUser().getLastName(),
			principalUser.providerUser().getEmail(),
			principalUser.providerUser().getProvider()
		);

		return new UsernamePasswordAuthenticationToken(
			customPrincipal,
			authentication.getCredentials(),
			authentication.getAuthorities()
		);
	}

	public void setSecurityContext(UsernamePasswordAuthenticationToken token) {
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	public void setSessionAttribute(HttpServletRequest request) {
		request.getSession().setAttribute(
			HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
			SecurityContextHolder.getContext()
		);
	}
}