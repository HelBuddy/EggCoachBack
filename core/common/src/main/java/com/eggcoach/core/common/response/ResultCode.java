package com.eggcoach.core.common.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultCode {
	String httpStatus;
	String getCode;
	String getMessage;
}
