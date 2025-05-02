package com.eggcoach.core.domain.account.model;

import java.time.LocalDateTime;

import com.eggcoach.core.common.account.UserStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSchedule {

	// PK
	private Long detail_seq;

	// 회원 FK
	private Long user_seq;

	// 선호 성별(회원일 시)
	private String pre_gender;

	// 선호 위치(회원일 시)
	private String pre_adderss;

	// 선호 시간대 시작(회원일 시)
	private LocalDateTime pre_start_time;

	// 선호 시간대 종료(회원일 시)
	private LocalDateTime pre_end_time;

	// 어필 (트레이너)
	private String appeal;

	// 가능 시간대 시작(트레이너 일 시)
	private LocalDateTime can_start_time;

	// 가능 시간대 종료(트레이너 일 시)
	private LocalDateTime can_end_time;

	// 헬스장 전경 시퀀스(트레이너 일  시)
	private Long ground_seq;

	// 난이도
	private Long lvl;

}
