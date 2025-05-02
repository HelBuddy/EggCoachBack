package com.eggcoach.infrastructure.account.service;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.account.dto.UserScheduleRequestDto;
import com.eggcoach.infrastructure.account.entity.UserScheduleEntity;
import com.eggcoach.infrastructure.account.repository.UserScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserScheduleService {

	private final UserScheduleRepository userScheduleRepository;

	public void registerSchedule(Long userSeq, UserScheduleRequestDto userScheduleRequestDto) {
		userScheduleRepository.save(new UserScheduleEntity().registerSchedule(userSeq, userScheduleRequestDto));
	}
}
