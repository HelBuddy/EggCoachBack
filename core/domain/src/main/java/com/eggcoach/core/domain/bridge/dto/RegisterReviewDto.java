package com.eggcoach.core.domain.bridge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterReviewDto {

	// 유저 아이디
	private Long userId;

	// 트레이너 아이디
	private Long trainerId;

	// 평점
	private Double rating;

	// 리뷰내용
	private String content;
}
