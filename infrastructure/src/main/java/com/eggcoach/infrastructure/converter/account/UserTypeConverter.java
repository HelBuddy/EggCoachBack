package com.eggcoach.infrastructure.converter.account;

import com.eggcoach.core.common.account.UserType;
import com.eggcoach.infrastructure.converter.EnumConverter;

import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class UserTypeConverter extends EnumConverter<UserType> {
	protected UserTypeConverter() {
		super(UserType.class);
	}
}
