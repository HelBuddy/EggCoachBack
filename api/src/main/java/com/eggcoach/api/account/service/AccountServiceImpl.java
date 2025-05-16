package com.eggcoach.api.account.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eggcoach.core.common.account.AccountErrorCode;
import com.eggcoach.core.common.account.AccountSuccessCode;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.LikeDto;
import com.eggcoach.core.domain.account.dto.SignUpDto;
import com.eggcoach.core.domain.account.dto.UserScheduleRequestDto;
import com.eggcoach.core.domain.account.model.User;
import com.eggcoach.core.domain.account.service.UserService;
import com.eggcoach.core.domain.bridge.dto.RegisterReviewDto;
import com.eggcoach.core.domain.bridge.dto.TrainerUserReviewDto;
import com.eggcoach.core.domain.security.adapter.UserServicePort;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.service.LikeService;
import com.eggcoach.infrastructure.account.service.UserScheduleService;
import com.eggcoach.infrastructure.account.service.UserServiceImpl;
import com.eggcoach.infrastructure.brigde.entity.TrainerUserReviewEntity;
import com.eggcoach.infrastructure.brigde.service.TrainerUserReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final UserService userService;

	private final UserServicePort userServicePort;

	private final UserScheduleService userScheduleService;

	private final TrainerUserReviewService trainerUserReviewService;

	private final UserServiceImpl userServiceImpl;

	private final LikeService likeService;

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

		if (userService.isDuplicatedUserEmail(
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

	@Override
	public List<TrainerUserReviewDto> getAllTrainerReviews(Long trainerId) {
		return trainerUserReviewService.getTrainerReviews(trainerId);
	}

	@Override
	public List<TrainerUserReviewDto> getAllUserReviews(String userEmail) {
		User user = userServicePort.findByUserEmail(userEmail);
		return trainerUserReviewService.getUserReviews(user.getUserSeq());
	}

	@Override
	public Double getTrainerAvgRatingReview(Long trainerId) {
		return trainerUserReviewService.getTrainerAverageRating(trainerId);
	}

	@Override
	public TrainerUserReviewDto registerReview(CustomPrincipal customPrincipal, RegisterReviewDto registerReviewDto) {

		UserEntity userEntity = userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail()).orElseThrow();

		UserEntity trainerEntity = userServiceImpl.getUserEntityByUserId(registerReviewDto.getTrainerId()).orElseThrow();

		TrainerUserReviewEntity trainerUserReviewEntity = trainerUserReviewService.registerReview(
			userEntity, trainerEntity, registerReviewDto);

		Double trainerAverageRating = trainerUserReviewService.getTrainerAverageRating(trainerEntity.getUserSeq());

		return new TrainerUserReviewDto(
			trainerUserReviewEntity.getTrainerUserReviewSeq(),
			trainerUserReviewEntity.getUserEntity().getUserSeq(),
			trainerUserReviewEntity.getTrainerEntity().getUserSeq(),
			trainerUserReviewEntity.getUserEntity().getNickName(),
			trainerUserReviewEntity.getRating(),
			trainerAverageRating,
			trainerUserReviewEntity.getContent(),
			trainerUserReviewEntity.getUpdateAt(),
			trainerUserReviewEntity.getCreateAt()
		);
	}

	@Override
	public ResultCode registerLike(CustomPrincipal customPrincipal, LikeDto likeDto) {
		UserEntity userEntity = userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail()).orElseThrow();

		likeService.registerLike(userEntity, likeDto);

		return ResultCode.builder()
			.httpStatus(String.valueOf(HttpStatus.OK.value()))
			.code(CommonCode.COMMON_SUCCESS_CODE.getCode())
			.message(CommonCode.COMMON_SUCCESS_CODE.getMessage())
			.build();
	}

}
