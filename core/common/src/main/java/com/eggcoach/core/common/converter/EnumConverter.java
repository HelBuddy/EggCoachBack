package com.eggcoach.core.common.converter;

public interface EnumConverter<E, String> {

	String commonConvertToDatabaseColumn(E attribute);

	E commonConvertToEntityAttribute(String dbData);
}
