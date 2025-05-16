package com.eggcoach.core.domain.trainer.model;

import java.time.LocalDateTime;

public class TrainerQna {

	// PK
	private Long trainerQnaSeq;

	// 질문
	private String question;

	// 답변
	private String answer;

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;
}
