package com.eggcoach.api.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eggcoach.core.common.account.UserType;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.board.dto.CategoryDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.service.UserServiceImpl;
import com.eggcoach.infrastructure.board.entity.CategoryBoardEntity;
import com.eggcoach.infrastructure.category.service.CategoryBoardInfraService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryBoardServiceImpl implements CategoryBoardService {

	private final CategoryBoardInfraService categoryBoardInfraService;

	private final UserServiceImpl userServiceImpl;

	@Override
	public ResultCode registerCategory(CategoryDto categoryDto, CustomPrincipal customPrincipal) {
		try {
			UserEntity userEntity = userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail()).orElseThrow();

			// 관리자 권한 체크 (필요한 경우)
			if (!"ROLE_ADMIN".equals(customPrincipal.getRole())) {
				return ResultCode.builder()
					.httpStatus(String.valueOf(HttpStatus.FORBIDDEN.value()))
					.code("PERMISSION_DENIED")
					.message("카테고리 등록 권한이 없습니다.")
					.build();
			}

			categoryBoardInfraService.saveCategory(categoryDto, userEntity);

			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.OK.value()))
				.code(CommonCode.COMMON_SUCCESS_CODE.getCode())
				.message("카테고리가 성공적으로 등록되었습니다.")
				.build();
		} catch (Exception e) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.code("CATEGORY_REGISTER_FAILED")
				.message("카테고리 등록에 실패했습니다: " + e.getMessage())
				.build();
		}
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		return categoryBoardInfraService.getAllCategories().stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());
	}

	@Override
	public Page<CategoryDto> getAllCategories(Pageable pageable) {
		return categoryBoardInfraService.getAllCategories(pageable)
			.map(this::convertToDto);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		CategoryBoardEntity entity = categoryBoardInfraService.getCategoryById(categoryId)
			.orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다"));

		return convertToDto(entity);
	}

	@Override
	public ResultCode updateCategory(CategoryDto categoryDto, CustomPrincipal customPrincipal) {
		try {

			if (UserType.ADMIN.getCode().equals(customPrincipal.getRole())) {
				return ResultCode.builder()
					.httpStatus(String.valueOf(HttpStatus.FORBIDDEN.value()))
					.code("PERMISSION_DENIED")
					.message("카테고리 수정 권한이 없습니다.")
					.build();
			}

			if (!categoryBoardInfraService.existsCategory(categoryDto.getCategorySeq())) {
				return ResultCode.builder()
					.httpStatus(String.valueOf(HttpStatus.NOT_FOUND.value()))
					.code("CATEGORY_NOT_FOUND")
					.message("수정할 카테고리를 찾을 수 없습니다.")
					.build();
			}

			categoryBoardInfraService.updateCategory(categoryDto);

			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.OK.value()))
				.code(CommonCode.COMMON_SUCCESS_CODE.getCode())
				.message("카테고리가 성공적으로 수정되었습니다.")
				.build();
		} catch (Exception e) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.code("CATEGORY_UPDATE_FAILED")
				.message("카테고리 수정에 실패했습니다: " + e.getMessage())
				.build();
		}
	}

	@Override
	public ResultCode deleteCategory(Long categoryId, CustomPrincipal customPrincipal) {
		try {

			if (UserType.ADMIN.getCode().equals(customPrincipal.getRole())) {
				return ResultCode.builder()
					.httpStatus(String.valueOf(HttpStatus.FORBIDDEN.value()))
					.code("PERMISSION_DENIED")
					.message("카테고리 삭제 권한이 없습니다.")
					.build();
			}

			if (!categoryBoardInfraService.existsCategory(categoryId)) {
				return ResultCode.builder()
					.httpStatus(String.valueOf(HttpStatus.NOT_FOUND.value()))
					.code("CATEGORY_NOT_FOUND")
					.message("삭제할 카테고리를 찾을 수 없습니다.")
					.build();
			}


			categoryBoardInfraService.deleteCategory(categoryId);

			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.OK.value()))
				.code(CommonCode.COMMON_SUCCESS_CODE.getCode())
				.message("카테고리가 성공적으로 삭제되었습니다.")
				.build();
		} catch (Exception e) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.code("CATEGORY_DELETE_FAILED")
				.message("카테고리 삭제에 실패했습니다: " + e.getMessage())
				.build();
		}
	}

	private CategoryDto convertToDto(CategoryBoardEntity entity) {
		return new CategoryDto(
			entity.getCategoryBoardSeq(),
			entity.getCategoryName(),
			entity.getDescription(),
			entity.getDisplayOrder(),
			entity.getUseYn(),
			entity.getCreatorId()
		);
	}
}