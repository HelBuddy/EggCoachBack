package com.eggcoach.core.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryNameDto {

	// PK
	private Long categoryBoard;

	// 카테고리명
	private String categoryName;
}
