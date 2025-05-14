package com.eggcoach.core.domain.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpGymMakerDto {

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
}
