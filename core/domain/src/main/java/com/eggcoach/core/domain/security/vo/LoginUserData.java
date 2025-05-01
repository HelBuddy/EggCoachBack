package com.eggcoach.core.domain.security.vo;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class LoginUserData {

	private String userEmail;

	private String role;

}
