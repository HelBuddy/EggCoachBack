package com.eggcoach.core.domain.bridge.model;

import java.time.LocalDateTime;

public class UserGymBridge {

	// User PK
	private Long userSeq;

	// Gym PK
	private Long gym_seq;

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;
}
