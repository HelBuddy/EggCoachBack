package com.eggcoach.core.domain.bridge.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrainerUserReviewDto {

	// PK
	private Long review_seq;

	// 유저 아이디
	private Long userId;

	// 트레이너 아이디
	private Long trainerId;

	// 유저 닉네임
	private String nickname;

	// 평점
	private Double rating;

	// 평균 평점
	private Double avgRating;

	// 리뷰내용
	private String content;

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;
}
