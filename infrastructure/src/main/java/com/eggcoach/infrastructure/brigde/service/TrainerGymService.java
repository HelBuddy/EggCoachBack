package com.eggcoach.infrastructure.brigde.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.brigde.entity.TrainerGymEntity;
import com.eggcoach.infrastructure.brigde.repository.TrainerGymRepository;
import com.eggcoach.infrastructure.gym.entity.GymEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerGymService {

	private final TrainerGymRepository trainerGymRepository;

	public void setTrainerAtGym(UserEntity userEntity, GymEntity gymEntity) {
		TrainerGymEntity trainerGymEntity = new TrainerGymEntity().setTrainerAtGym(userEntity, gymEntity);
		userEntity.addGym(trainerGymEntity);
		gymEntity.addGym(trainerGymEntity);
		trainerGymRepository.save(trainerGymEntity);
	}

	public List<TrainerGymEntity> findAllByGymSeqWithUser(Long gymSeq) {
		return trainerGymRepository.findAllByGymSeqWithUser(gymSeq);
	}
}
