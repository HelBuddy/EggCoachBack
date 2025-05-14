package com.eggcoach.api.gym.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eggcoach.core.common.account.AccountErrorCode;
import com.eggcoach.core.common.account.AccountSuccessCode;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.gym.dto.GymMarkerDto;
import com.eggcoach.core.domain.gym.dto.SignUpGymMakerDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {

	private final com.eggcoach.infrastructure.gym.service.GymService gymService;

	@Override
	public ResultCode registerGym(SignUpGymMakerDto signUpGymMakerDto) {

		//todo 실패시 처리 설정
		gymService.registerGym(signUpGymMakerDto);

		return ResultCode.builder()
			.httpStatus(String.valueOf(HttpStatus.OK.value()))
			.code(AccountSuccessCode.SIGNUP_SUCCESS_CODE.getCode())
			.message(AccountSuccessCode.SIGNUP_SUCCESS_CODE.getMessage())
			.build();
	}

	@Override
	public List<GymMarkerDto> getAllGymBounds(Double minX, Double maxX, Double minY, Double maxY) {

		List<GymMarkerDto> list = gymService.getAllGymBounds(minX, maxX, minY, maxY)
			.stream()
			.map(item -> new GymMarkerDto(
				item.getGym_seq(),
				item.getGym_name(),
				item.getLng(),
				item.getLat(),
				item.getAddress(),
				item.getDepth1(),
				item.getDepth2(),
				item.getDepth3()
			)).toList();

		return list;
	}
}
