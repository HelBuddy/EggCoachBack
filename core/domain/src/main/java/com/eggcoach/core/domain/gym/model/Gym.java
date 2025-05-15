package com.eggcoach.core.domain.gym.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Gym {

	// PK
	private Long gym_seq;

	// 헬스장 이름
	private String gym_name;

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

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;

}
