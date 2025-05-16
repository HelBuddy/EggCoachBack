package com.eggcoach.infrastructure.trainer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.trainer.dto.TrainerQnaViewDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.trainer.entity.TrainerQnaEntity;
import com.eggcoach.infrastructure.trainer.repository.TrainerQnaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerInfraService {

	private final TrainerQnaRepository trainerQnaRepository;

	public void registerTrainerQna(TrainerQnaViewDto trainerQnaViewDto, UserEntity userEntity) {
		trainerQnaRepository.save(new TrainerQnaEntity().registerTrainerQnaEntity(trainerQnaViewDto, userEntity));
	}

	public List<TrainerQnaEntity> getAllTrainerQna(Long trainerId) {
		return trainerQnaRepository.findAllByUserEntity_UserSeq(trainerId);
	}
}
