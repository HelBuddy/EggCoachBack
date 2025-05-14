package com.eggcoach.api.gym.service;

import java.util.List;

import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.gym.dto.GymMarkerDto;
import com.eggcoach.core.domain.gym.dto.SignUpGymMakerDto;

public interface GymService {

	ResultCode registerGym(SignUpGymMakerDto signUpGymMakerDto);

	List<GymMarkerDto> getAllGymBounds(Double minX, Double maxX, Double minY, Double maxY);
}
