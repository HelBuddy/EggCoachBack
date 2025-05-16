package com.eggcoach.infrastructure.brigde.entity;

import java.time.LocalDateTime;

import com.eggcoach.core.domain.bridge.dto.RegisterReviewDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;

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
@Table(name = "trainer_review")
public class TrainerUserReviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trainerUserReviewSeq;

	// 유저 아이디
	@ManyToOne
	@JoinColumn(name = "user_seq", referencedColumnName = "userSeq")
	private UserEntity userEntity;

	// 트레이너 아이디
	@ManyToOne
	@JoinColumn(name = "trainer_seq", referencedColumnName = "userSeq")
	private UserEntity trainerEntity;

	// 유저 pk
	@Column(name = "rating")
	private Double rating;

	// 헬스장 PK
	@Column(name = "content")
	private String content;

	// 생성 날짜
	@Column(name = "create_at")
	private LocalDateTime createAt;

	// 수정 날짜
	@Column(name = "update_at")
	private LocalDateTime updateAt;

	public TrainerUserReviewEntity registerReview(
		UserEntity userEntity,
		UserEntity trainerEntity,
		RegisterReviewDto registerReviewDto
	) {
		this.userEntity = userEntity;
		this.trainerEntity = trainerEntity;
		this.content = registerReviewDto.getContent();
		this.rating = registerReviewDto.getRating();
		this.createAt = LocalDateTime.now();
		this.updateAt = LocalDateTime.now();
		return this;
	}
}
