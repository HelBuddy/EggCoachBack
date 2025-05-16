package com.eggcoach.core.domain.trainer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrainerQnaDaoDto {

	// PK
	private Long trainerQnaSeq;

	// 질문
	private String question;

	// 답변
	private String answer;

}
