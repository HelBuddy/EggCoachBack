package com.eggcoach.infrastructure.converter;

import com.eggcoach.core.common.converter.GenericEnumCodeConverter;

import jakarta.persistence.AttributeConverter;

public abstract class EnumConverter<E extends Enum<E>> extends GenericEnumCodeConverter<E> implements AttributeConverter<E, String> {

	protected EnumConverter(Class<E> enumClass) {
		super(enumClass);
	}

	@Override
	public String convertToDatabaseColumn(E e) {
		return super.commonConvertToDatabaseColumn(e);
	}

	@Override
	public E convertToEntityAttribute(String s) {
		return super.commonConvertToEntityAttribute(s);
	}
}
