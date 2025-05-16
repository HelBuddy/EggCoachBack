package com.eggcoach.infrastructure.account.entity;

import java.time.LocalDateTime;

import com.eggcoach.core.domain.account.dto.LikeDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "trainer_like")
public class LikeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeSeq;

	// 유저 아이디
	@ManyToOne
	@JoinColumn(name = "user_seq")
	private UserEntity userEntity;

	// 트레이너 아이디
	@Column(name = "trainer_seq")
	private Long trainerId;

	// 생성 날짜
	@Column(name = "create_at")
	private LocalDateTime createAt;

	// 수정 날짜
	@Column(name = "update_at")
	private LocalDateTime updateAt;

	public LikeEntity registerLike(UserEntity userEntity, LikeDto likeDto) {
		this.userEntity = userEntity;
		this.trainerId = likeDto.getTrainerSeq();
		return this;
	}
}
