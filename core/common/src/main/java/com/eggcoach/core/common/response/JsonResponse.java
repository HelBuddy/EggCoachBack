package com.eggcoach.core.common.response;

import lombok.Getter;

@Getter
public class JsonResponse<T> {
	private String code;
	private String message;
	private T data;

	public JsonResponse(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * 데이터와 함께 응답 생성
	 */
	public static <T> JsonResponse<T> of(String code, String message, T data) {
		return new JsonResponse<>(code, message, data);
	}

	/**
	 * 메시지/코드만 있는 응답 생성
	 */
	public static JsonResponse<Void> of(String code, String message) {
		return new JsonResponse<>(code, message, null);
	}

}
