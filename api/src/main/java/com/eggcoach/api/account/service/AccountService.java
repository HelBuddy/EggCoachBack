package com.eggcoach.api.account.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.SignUpDto;
import com.eggcoach.core.domain.account.dto.UserScheduleRequestDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;

public interface AccountService {

	ResultCode signUp(SignUpDto signUpDTO, UserScheduleRequestDto userScheduleRequestDto, MultipartFile profileImg) throws IOException;

	void registerProfileImg(MultipartFile profileImg, String imageNm) throws IOException;

	void registerUserSchedule(Long userSeq,UserScheduleRequestDto userScheduleRequestDto);

}
