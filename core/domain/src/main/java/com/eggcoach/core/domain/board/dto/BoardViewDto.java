package com.eggcoach.core.domain.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardViewDto {

	// 보드 PK
	private Long boardSeq;

	// 카테고리 아이디
	private Long categoryId;

	// 유저 아이디
	private Long userId;

	// 평점
	private String categoryName;

	// 공개 여부
	private String lockYn;

	// 공지 여부
	private String noticeYn;

	// 비공개 패스워드
	private String lockPassword;

	// 제목
	private String title;

	// 내용
	private String content;

	// 조회수
	private String view_cnt;

	// 좋아요 수
	private String favorite_cnt;

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;

}
