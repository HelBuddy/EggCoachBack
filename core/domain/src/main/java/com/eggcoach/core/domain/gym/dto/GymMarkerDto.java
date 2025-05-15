package com.eggcoach.core.domain.gym.dto;

import java.util.ArrayList;
import java.util.List;

import com.eggcoach.core.domain.account.dto.UserGymDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymMarkerDto {

	// pk
	private Long gymSeq;

	// 주소
	private String gymName;

	// 경도
	private Double lng;

	// 위도
	private Double lat;

	// 주소
	private String address;

	// 시
	private String depth1;

	// 구
	private String depth2;

	// 동
	private String depth3;

	// 헬스장에 연결된 트레이너
	private List<UserGymDto> userGymDto = new ArrayList<>();
}
