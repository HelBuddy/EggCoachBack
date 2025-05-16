package com.eggcoach.core.domain.account.model;

import java.time.LocalDateTime;

public class Like {

	// PK
	private Long like_seq;

	// 유저 아이디
	private Long user_id;

	// 트레이너 아이디
	private Long trainer_id;

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;
}
