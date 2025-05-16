package com.eggcoach.core.domain.account.model;

import java.time.LocalDateTime;

public class Review {

	// PK
	private Long review_seq;

	// 유저 아이디
	private Long user_id;

	// 트레이너 아이디
	private Long trainer_id;

	// 평점
	private Double rating;

	// 리뷰내용
	private String content;

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;

}
