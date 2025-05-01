package com.eggcoach.core.domain.security.vo;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class CustomPrincipal implements Serializable {
	private final String role;
	private final String name;
	private final String email;
	private final String vendor;

	public CustomPrincipal(String role, String name, String email,String vendor) {
		this.role = role;
		this.name = name;
		this.email = email;
		this.vendor = vendor;
	}

}

