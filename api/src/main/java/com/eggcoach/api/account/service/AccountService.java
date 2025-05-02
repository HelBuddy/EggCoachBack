package com.eggcoach.api.account.service;

import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.SignUpDto;

public interface AccountService {

	ResultCode signUp(SignUpDto signUpDTO);

}
