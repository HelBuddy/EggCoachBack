package com.eggcoach.api.account.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eggcoach.core.common.account.AccountErrorCode;
import com.eggcoach.core.common.account.AccountSuccessCode;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.SignUpDto;
import com.eggcoach.core.domain.account.service.UserService;
import com.eggcoach.core.domain.security.adapter.UserServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final UserService userService;

	private final UserServicePort userServicePort;

	@Override
	public ResultCode signUp(SignUpDto signUpDTO) {

		if (userService.isDuplicatedUserEmail(signUpDTO.getUserEmail())) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()))
				.getCode(AccountErrorCode.DUPLICATE_EMAIL.getCode())
				.getMessage(AccountErrorCode.DUPLICATE_EMAIL.getMessage())
				.build();
		}

		userServicePort.signUp(signUpDTO);

		return ResultCode.builder()
			.httpStatus(String.valueOf(HttpStatus.OK.value()))
			.getCode(AccountSuccessCode.SIGNUP_SUCCESS_CODE.getCode())
			.getMessage(AccountSuccessCode.SIGNUP_SUCCESS_CODE.getMessage())
			.build();
	}

}
