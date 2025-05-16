package com.eggcoach.core.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	// 카테고리 Pk
	private Long categorySeq;

	// 카테고리 이름
	private String categoryName;

	// 카테고리 설명
	private String description;

	// 카테고리 순서
	private Integer displayOrder;

	// 카테고리 사용여부
	private String useYn;

	// 카테고리 만든사람
	private String creatorId;
}