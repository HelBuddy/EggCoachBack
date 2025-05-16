package com.eggcoach.infrastructure.trainer.entity;

import java.time.LocalDateTime;

import com.eggcoach.core.domain.trainer.dto.TrainerQnaViewDto;
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
@Table(name = "trainer_qna")
public class TrainerQnaEntity {

	// PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trainerQnaSeq;

	// 질문
	@ManyToOne
	@JoinColumn(name = "user_seq")
	private UserEntity userEntity;

	// 질문
	@Column(name = "question")
	private String question;

	// 답변
	@Column(name = "answer")
	private String answer;

	// 생성 날짜
	@Column(name = "create_at")
	private LocalDateTime createAt;

	// 수정 날짜
	@Column(name = "update_at")
	private LocalDateTime updateAt;

	public TrainerQnaEntity registerTrainerQnaEntity(TrainerQnaViewDto trainerQnaViewDto, UserEntity userEntity) {
		this.userEntity = userEntity;
		this.question = trainerQnaViewDto.getQuestion();
		this.answer = trainerQnaViewDto.getAnswer();
		this.updateAt = LocalDateTime.now();
		this.createAt = LocalDateTime.now();
		return this;
	}
}
