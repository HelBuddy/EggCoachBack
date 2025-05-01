package com.eggcoach.core.security.converter;

public interface ProviderUserConverter<T, R> {
	R converter(T t);
}