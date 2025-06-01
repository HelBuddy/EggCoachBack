package com.eggcoach.infrastructure.account.entity;

import java.time.LocalDateTime;

import com.eggcoach.core.domain.account.dto.UserScheduleRequestDto;
import com.eggcoach.core.domain.account.model.UserSchedule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_schedule")
public class UserScheduleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long detail_seq;

	// 회원 FK
	@Column(name = "user_seq")
	private Long userSeq;

	// 선호 성별(회원일 시)
	@Column(name = "pre_gender")
	private String pre_gender;

	// 선호 위치(회원일 시)
	@Column(name = "pre_adderss")
	private String pre_adderss;

	// 선호 시간대 시작(회원일 시)
	@Column(name = "pre_start_time")
	private LocalDateTime pre_start_time;

	// 선호 시간대 종료(회원일 시)
	@Column(name = "pre_end_time")
	private LocalDateTime pre_end_time;

	// 어필 (트레이너)
	@Column(name = "appeal")
	private String appeal;

	// 가능 시간대 시작(트레이너 일 시)
	@Column(name = "can_start_time")
	private LocalDateTime can_start_time;

	// 가능 시간대 종료(트레이너 일 시)
	@Column(name = "can_end_time")
	private LocalDateTime can_end_time;

	// 헬스장 전경 시퀀스(트레이너 일  시)
	@Column(name = "ground_seq")
	private Long ground_seq;

	// 난이도
	@Column(name = "lvl")
	private Long lvl;

	public UserSchedule toUserSchedule() {
		return UserSchedule.builder()
			.detail_seq(detail_seq)
			.user_seq(userSeq)
			.pre_gender(pre_gender)
			.pre_adderss(pre_adderss)
			.pre_start_time(pre_start_time)
			.pre_end_time(pre_end_time)
			.appeal(appeal)
			.can_start_time(can_start_time)
			.can_end_time(can_end_time)
			.ground_seq(ground_seq)
			.lvl(lvl)
			.build();
	}

	public UserScheduleEntity registerSchedule(Long userSeq, UserScheduleRequestDto userScheduleRequestDto) {
		this.userSeq = userSeq;
		this.pre_gender = userScheduleRequestDto.getPre_gender();
		this.pre_adderss = userScheduleRequestDto.getPre_adderss();
		this.appeal = userScheduleRequestDto.getAppeal();
		this.pre_start_time = userScheduleRequestDto.getPre_start_time();
		this.pre_end_time = userScheduleRequestDto.getPre_end_time();
		this.can_start_time = userScheduleRequestDto.getCan_start_time();
		this.can_end_time = userScheduleRequestDto.getPre_end_time();
		this.ground_seq = userScheduleRequestDto.getGround_seq();
		this.lvl = userScheduleRequestDto.getLvl();
		return this;
	}

}


