package com.eggcoach.api.trainer.service;

import java.util.List;

import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.domain.trainer.dto.TrainerDetailDto;
import com.eggcoach.core.domain.trainer.dto.TrainerQnaViewDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.trainer.entity.TrainerQnaEntity;

public interface TrainerService {

	void registerTrainerQna(TrainerQnaViewDto trainerQnaViewDto, CustomPrincipal customPrincipal);

	List<TrainerQnaEntity> getAllTrainerQna(Long trainerId);

	TrainerDetailDto getTrainerDetail(Long trainerId, CustomPrincipal customPrincipal);
}
