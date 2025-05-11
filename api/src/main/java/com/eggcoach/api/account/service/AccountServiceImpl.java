package com.eggcoach.api.account.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eggcoach.core.common.account.AccountErrorCode;
import com.eggcoach.core.common.account.AccountSuccessCode;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.SignUpDto;
import com.eggcoach.core.domain.account.dto.UserScheduleRequestDto;
import com.eggcoach.core.domain.account.model.User;
import com.eggcoach.core.domain.account.service.UserService;
import com.eggcoach.core.domain.security.adapter.UserServicePort;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.infrastructure.account.service.UserScheduleService;
import com.eggcoach.core.common.account.UserType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final UserService userService;

	private final UserServicePort userServicePort;

	private final UserScheduleService userScheduleService;

	@Value("${static.path.profile}")
	private String profilePath;

	/**
	 * 회원가입
	 * @param signUpDTO 회원 가입 정보
	 * @param userScheduleRequestDto 유저 스케줄 정보
	 * @param profileImg 유저 프로필 파일
	 * @return ResultCode 결과 코드
	 * @throws IOException
	 */
	@Transactional
	@Override
	public ResultCode signUp(SignUpDto signUpDTO, UserScheduleRequestDto userScheduleRequestDto
		, MultipartFile profileImg) throws IOException {

		if (!UserType.SOCIAL.getCode().equals(signUpDTO.getUserType()) && userService.isDuplicatedUserEmail(
			signUpDTO.getUserEmail())) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()))
				.code(AccountErrorCode.DUPLICATE_EMAIL.getCode())
				.message(AccountErrorCode.DUPLICATE_EMAIL.getMessage())
				.build();
		}

		if (profileImg != null && !profileImg.isEmpty()) {
			// 확장자 추출
			String originalFilename = profileImg.getOriginalFilename();
			// 기본 확장자
			String extension = "png";
			if (originalFilename != null && originalFilename.contains(".")) {
				extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			}

			signUpDTO.setProfileImg(signUpDTO.getUserEmail() + "_" + "profileImg" + extension);
		}

		User user = userServicePort.signUp(signUpDTO);

		registerUserSchedule(user.getUserSeq(), userScheduleRequestDto);

		// todo 이미지 저장실패시 DB삭제 로직 필요
		registerProfileImg(profileImg, signUpDTO.getProfile());

		return ResultCode.builder()
			.httpStatus(String.valueOf(HttpStatus.OK.value()))
			.code(AccountSuccessCode.SIGNUP_SUCCESS_CODE.getCode())
			.message(AccountSuccessCode.SIGNUP_SUCCESS_CODE.getMessage())
			.build();
	}

	/**
	 * 이미지 저장
	 * @param profileImg 프로필 이미지 파일
	 * @param imageNm 이미지 이름
	 * @throws IOException
	 */
	@Override
	public void registerProfileImg(MultipartFile profileImg, String imageNm) throws IOException {
		if (imageNm == null || imageNm.isEmpty()) {
			return;
		}
		profileImg.transferTo(new File(profilePath + imageNm));
	}

	/**
	 * 유저 운동 가능시간 스케줄 등록
	 * @param userSeq 유저 PK
	 * @param userScheduleRequestDto 유저 스케줄 정보
	 */
	@Override
	public void registerUserSchedule(Long userSeq, UserScheduleRequestDto userScheduleRequestDto) {
		if (userSeq == null) {
			return;
		}
		userScheduleService.registerSchedule(userSeq, userScheduleRequestDto);
	}

}
