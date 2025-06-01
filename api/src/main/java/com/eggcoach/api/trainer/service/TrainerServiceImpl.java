package com.eggcoach.api.trainer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.domain.trainer.dto.TrainerDetailDto;
import com.eggcoach.core.domain.trainer.dto.TrainerQnaViewDto;
import com.eggcoach.core.domain.trainer.dto.TrainerScheduleDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.service.LikeService;
import com.eggcoach.infrastructure.account.service.UserServiceImpl;
import com.eggcoach.infrastructure.brigde.service.TrainerUserReviewService;
import com.eggcoach.infrastructure.trainer.entity.TrainerQnaEntity;
import com.eggcoach.infrastructure.trainer.service.TrainerInfraService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

	private final TrainerInfraService trainerInfraService;

	private final UserServiceImpl userServiceImpl;

	private final TrainerUserReviewService trainerUserReviewService;

	private final LikeService likeService;

	@Override
	public void registerTrainerQna(TrainerQnaViewDto trainerQnaViewDto, CustomPrincipal customPrincipal) {
		//todo 유저 없는경우 처리
		UserEntity userEntity = userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail()).orElseThrow();

		trainerInfraService.registerTrainerQna(trainerQnaViewDto, userEntity);
	}

	@Override
	public List<TrainerQnaEntity> getAllTrainerQna(Long trainerId) {
		return trainerInfraService.getAllTrainerQna(trainerId);
	}

	@Override
	public TrainerDetailDto getTrainerDetail(Long trainerId, CustomPrincipal customPrincipal) {

		UserEntity loginUser = null;

		String likeYn = "N";

		// 로그인 유저 정보 (로그인 시)
		if (customPrincipal != null && !"ANONYMOUSE".equals(customPrincipal.getEmail())) {

			loginUser = userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail()).orElseThrow();

			// 좋아요 여부
			likeYn = likeService.existsByUserEntity_UserSeqAndTrainerId(loginUser.getUserSeq(), trainerId) ? "Y" : "N";
		}

		// 트레이너 유저 정보
		UserEntity trainerUser = userServiceImpl.getUserEntityByUserId(trainerId).orElseThrow();

		// 트레이너 스케줄
		List<TrainerScheduleDto> allTrainerSchedules = trainerInfraService.getAllTrainerSchedules(trainerId);

		// 트레이너 평점
		Double trainerAverageRating = trainerUserReviewService.getTrainerAverageRating(trainerId);

		new TrainerDetailDto(
			trainerUser.getUserSeq(),
			trainerUser.getUserName(),
			trainerUser.getGender(),
			trainerUser.getStatus(),
			trainerUser.getUserType(),
			trainerUser.getNickName(),
			trainerUser.getProfile(),
			trainerUser.getCreateAt(),
			trainerUser.getUpdateAt(),
			trainerUser.getSocialProvider(),
			trainerUser.getAge(),
			trainerUser.getAppeal(),
			trainerAverageRating,
			likeYn,
			allTrainerSchedules
		);
		return null;
	}
}
