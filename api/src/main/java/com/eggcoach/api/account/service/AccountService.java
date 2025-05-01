package com.eggcoach.api.account.service;

import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.SignUpDTO;

public interface AccountService {

	ResultCode signUp(SignUpDTO signUpDTO);

}
