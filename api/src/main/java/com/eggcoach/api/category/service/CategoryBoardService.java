package com.eggcoach.api.category.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.board.dto.CategoryDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;

public interface CategoryBoardService {

	// 카테고리 등록
	ResultCode registerCategory(CategoryDto categoryDto, CustomPrincipal customPrincipal);

	// 카테고리 목록 조회
	List<CategoryDto> getAllCategories();

	// 페이징 처리된 카테고리 목록 조회
	Page<CategoryDto> getAllCategories(Pageable pageable);

	// 카테고리 상세 조회
	CategoryDto getCategoryById(Long categoryId);

	// 카테고리 수정
	ResultCode updateCategory(CategoryDto categoryDto, CustomPrincipal customPrincipal);

	// 카테고리 삭제
	ResultCode deleteCategory(Long categoryId, CustomPrincipal customPrincipal);
}