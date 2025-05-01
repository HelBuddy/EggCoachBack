package com.eggcoach.core.domain.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDTO {
	private String userEmail;
	private String password;
}
