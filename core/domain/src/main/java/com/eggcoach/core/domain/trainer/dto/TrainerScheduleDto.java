package com.eggcoach.core.domain.trainer.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerScheduleDto {

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
