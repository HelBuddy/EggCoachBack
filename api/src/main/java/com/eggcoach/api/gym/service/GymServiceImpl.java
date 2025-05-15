package com.eggcoach.api.gym.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eggcoach.core.common.account.AccountErrorCode;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.UserGymDto;
import com.eggcoach.core.domain.gym.dto.GymMarkerDto;
import com.eggcoach.core.domain.gym.dto.SignUpGymMakerDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.service.UserServiceImpl;
import com.eggcoach.infrastructure.brigde.entity.TrainerGymEntity;
import com.eggcoach.infrastructure.brigde.service.TrainerGymService;
import com.eggcoach.infrastructure.gym.entity.GymEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {

	private final com.eggcoach.infrastructure.gym.service.GymService gymService;

	private final TrainerGymService trainerGymService;

	private final UserServiceImpl userServiceImpl;

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
	public List<GymMarkerDto> getAllGymBounds(Double minX, Double maxX, Double minY, Double maxY) {

		List<GymMarkerDto> list = gymService.getAllGymBounds(minX, maxX, minY, maxY)
			.stream()
			.map(item -> {

				List<TrainerGymEntity> allByGymSeqWithUser = trainerGymService.findAllByGymSeqWithUser(
					item.getGym_seq());

				return new GymMarkerDto(
					item.getGym_seq(),
					item.getGym_name(),
					item.getLng(),
					item.getLat(),
					item.getAddress(),
					item.getDepth1(),
					item.getDepth2(),
					item.getDepth3(),

					allByGymSeqWithUser.stream().map(trainerGymEntity ->
						 new UserGymDto(
							trainerGymEntity.getUserEntity().getUserSeq(),
							trainerGymEntity.getUserEntity().getUserName(),
							trainerGymEntity.getUserEntity().getGender(),
							trainerGymEntity.getUserEntity().getAge()
						)).toList()
				);
			}).toList();

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
