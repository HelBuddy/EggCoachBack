package com.eggcoach.infrastructure.converter.account;

import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.infrastructure.converter.EnumConverter;

import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class OAuthVendorConverter extends EnumConverter<OAuthVendor> {

	protected OAuthVendorConverter() {
		super(OAuthVendor.class);
	}
}