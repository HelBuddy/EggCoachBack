package com.eggcoach.api.trainer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.domain.trainer.dto.TrainerQnaViewDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.service.UserServiceImpl;
import com.eggcoach.infrastructure.trainer.entity.TrainerQnaEntity;
import com.eggcoach.infrastructure.trainer.service.TrainerInfraService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

	private final TrainerInfraService trainerInfraService;

	private final UserServiceImpl userServiceImpl;

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
}
