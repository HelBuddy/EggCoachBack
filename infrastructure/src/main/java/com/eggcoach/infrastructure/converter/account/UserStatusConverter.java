package com.eggcoach.infrastructure.converter.account;

import com.eggcoach.core.common.account.UserStatus;
import com.eggcoach.infrastructure.converter.EnumConverter;

import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class UserStatusConverter extends EnumConverter<UserStatus> {

	protected UserStatusConverter() {
		super(UserStatus.class);
	}
}
