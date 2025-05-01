package com.eggcoach.core.common.converter;

import java.lang.reflect.Method;

public abstract class GenericEnumCodeConverter<E extends Enum<E>> implements EnumConverter<E, String> {

	private final Class<E> enumClass;

	protected GenericEnumCodeConverter(Class<E> enumClass) {
		this.enumClass = enumClass;
	}

	@Override
	public String commonConvertToDatabaseColumn(E attribute) {
		if (attribute == null) return null;
		try {
			Method method = enumClass.getMethod("getCode");
			return (String) method.invoke(attribute);
		} catch (Exception e) {
			throw new RuntimeException("Enum does not have getCode() method.", e);
		}
	}

	@Override
	public E commonConvertToEntityAttribute(String dbData) {
		if (dbData == null) return null;
		try {
			Method method = enumClass.getMethod("fromCode", String.class);
			return enumClass.cast(method.invoke(null, dbData));
		} catch (Exception e) {
			throw new RuntimeException("Enum does not have static fromCode(String) method.", e);
		}
	}
}
