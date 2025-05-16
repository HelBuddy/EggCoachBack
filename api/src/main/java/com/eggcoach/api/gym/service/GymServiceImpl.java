package com.eggcoach.api.gym.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eggcoach.core.common.account.AccountErrorCode;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.LikeDto;
import com.eggcoach.core.domain.account.dto.UserGymDto;
import com.eggcoach.core.domain.gym.dto.GymMarkerDto;
import com.eggcoach.core.domain.gym.dto.SignUpGymMakerDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.service.LikeService;
import com.eggcoach.infrastructure.account.service.UserServiceImpl;
import com.eggcoach.infrastructure.brigde.entity.TrainerGymEntity;
import com.eggcoach.infrastructure.brigde.service.TrainerGymService;
import com.eggcoach.infrastructure.brigde.service.TrainerUserReviewService;
import com.eggcoach.infrastructure.gym.entity.GymEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {

	private final com.eggcoach.infrastructure.gym.service.GymService gymService;

	private final TrainerGymService trainerGymService;

	private final UserServiceImpl userServiceImpl;

	private final TrainerUserReviewService trainerUserReviewService;

	private final LikeService likeService;

	@Override
	public ResultCode registerGym(SignUpGymMakerDto signUpGymMakerDto) {

		//todo 실패시 처리 설정
		gymService.registerGym(signUpGymMakerDto);

		return ResultCode.builder()
			.httpStatus(String.valueOf(HttpStatus.OK.value()))
			.code(CommonCode.COMMON_SUCCESS_CODE.getCode())
			.message(CommonCode.COMMON_SUCCESS_CODE.getMessage())
			.build();
	}

	@Override
	public List<GymMarkerDto> getAllGymBounds(Double minX, Double maxX, Double minY, Double maxY, CustomPrincipal customPrincipal) {

		List<LikeDto> allUserLike = new ArrayList<>();

		if (customPrincipal != null && !"ANONYMOUSE".equals(customPrincipal.getEmail())) {
			userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail())
				.ifPresent(user -> {
					allUserLike.addAll(likeService.getAllUserLike(user.getUserSeq()));
				});
		}

		final Set<Long> likedTrainerIds = allUserLike.stream()
			.map(LikeDto::getTrainerSeq)
			.collect(Collectors.toSet());

		List<GymMarkerDto> list = gymService.getAllGymBounds(minX, maxX, minY, maxY)
			.stream()
			.map(item -> {
				List<TrainerGymEntity> allByGymSeqWithUser = trainerGymService.findAllByGymSeqWithUser(
					item.getGym_seq());

				List<UserGymDto> trainers = allByGymSeqWithUser.stream()
					.map(trainerGymEntity -> {
						UserEntity trainerEntity = trainerGymEntity.getUserEntity();
						Long trainerSeq = trainerEntity.getUserSeq();

						Double avgRating = trainerUserReviewService.getTrainerAverageRating(trainerSeq);

						String like = likedTrainerIds.contains(trainerSeq) ? "Y" : "N";

						return new UserGymDto(
							trainerSeq,
							trainerEntity.getUserName(),
							trainerEntity.getGender(),
							trainerEntity.getAge(),
							avgRating,
							like
						);
					})
					.collect(Collectors.toList());

				return new GymMarkerDto(
					item.getGym_seq(),
					item.getGym_name(),
					item.getLng(),
					item.getLat(),
					item.getAddress(),
					item.getDepth1(),
					item.getDepth2(),
					item.getDepth3(),
					trainers
				);
			})
			.collect(Collectors.toList());


		return list;
	}

	@Override
	@Transactional
	public ResultCode setTrainerAtGym(CustomPrincipal customPrincipal, GymMarkerDto gymMarkerDto) {

		UserEntity userEntity = userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail()).orElseThrow();

		if (!userEntity.getUserType().isTrainer()) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.OK.value()))
				.code(AccountErrorCode.USER_TYPE_NOT_TRAINER.getCode())
				.message(AccountErrorCode.USER_TYPE_NOT_TRAINER.getMessage())
				.build();
		}

		GymEntity gymEntity = gymService.getGymEntityById(gymMarkerDto.getGymSeq()).orElseThrow();

		trainerGymService.setTrainerAtGym(userEntity, gymEntity);

		return ResultCode.builder()
			.httpStatus(String.valueOf(HttpStatus.OK.value()))
			.code(CommonCode.COMMON_SUCCESS_CODE.getCode())
			.message(CommonCode.COMMON_SUCCESS_CODE.getMessage())
			.build();
	}
}
