package com.eggcoach.infrastructure.trainer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.trainer.dto.TrainerDetailDto;
import com.eggcoach.core.domain.trainer.dto.TrainerQnaViewDto;
import com.eggcoach.core.domain.trainer.dto.TrainerScheduleDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.repository.UserScheduleRepository;
import com.eggcoach.infrastructure.account.service.UserScheduleService;
import com.eggcoach.infrastructure.trainer.entity.TrainerQnaEntity;
import com.eggcoach.infrastructure.trainer.repository.TrainerQnaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerInfraService {

	private final TrainerQnaRepository trainerQnaRepository;

	private final UserScheduleRepository userScheduleRepository;

	public void registerTrainerQna(TrainerQnaViewDto trainerQnaViewDto, UserEntity userEntity) {
		trainerQnaRepository.save(new TrainerQnaEntity().registerTrainerQnaEntity(trainerQnaViewDto, userEntity));
	}

	public List<TrainerQnaEntity> getAllTrainerQna(Long trainerId) {
		return trainerQnaRepository.findAllByUserEntity_UserSeq(trainerId);
	}

	public List<TrainerScheduleDto> getAllTrainerSchedules(Long trainerId) {
		return userScheduleRepository.findByUser_seq(trainerId).stream().map(item ->
			new TrainerScheduleDto(item.getAppeal(), item.getCan_start_time(),
				item.getCan_end_time(),
				item.getGround_seq(), item.getLvl())
		).toList();
	}
}
