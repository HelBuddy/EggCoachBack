package com.eggcoach.infrastructure.gym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.gym.dto.SignUpGymMakerDto;
import com.eggcoach.infrastructure.gym.entity.GymEntity;
import com.eggcoach.infrastructure.gym.repository.GymRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymService {

	private final GymRepository gymRepository;

	public void registerGym(SignUpGymMakerDto signUpGymMakerDto) {

		gymRepository.save(new GymEntity().signupGymMarker(signUpGymMakerDto));
	}

	public List<GymEntity> getAllGymBounds(Double minX, Double maxX, Double minY, Double maxY) {
		return gymRepository.findAllByBounds(minX, maxX, minY, maxY);
	}
}
