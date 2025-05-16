package com.eggcoach.api.account.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.LikeDto;
import com.eggcoach.core.domain.account.dto.SignUpDto;
import com.eggcoach.core.domain.account.dto.UserScheduleRequestDto;
import com.eggcoach.core.domain.bridge.dto.RegisterReviewDto;
import com.eggcoach.core.domain.bridge.dto.TrainerUserReviewDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;

public interface AccountService {

	ResultCode signUp(SignUpDto signUpDTO, UserScheduleRequestDto userScheduleRequestDto, MultipartFile profileImg) throws IOException;

	void registerProfileImg(MultipartFile profileImg, String imageNm) throws IOException;

	void registerUserSchedule(Long userSeq,UserScheduleRequestDto userScheduleRequestDto);

	List<TrainerUserReviewDto> getAllTrainerReviews(Long trainerId);

	List<TrainerUserReviewDto> getAllUserReviews(String userEmail);

	Double getTrainerAvgRatingReview(Long trainerId);

	TrainerUserReviewDto registerReview(CustomPrincipal customPrincipal, RegisterReviewDto registerReviewDto);

	ResultCode registerLike(CustomPrincipal customPrincipal, LikeDto likeDto);
}
