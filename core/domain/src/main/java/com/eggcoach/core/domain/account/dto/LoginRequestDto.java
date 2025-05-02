package com.eggcoach.core.domain.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
	private String userEmail;
	private String password;
}
