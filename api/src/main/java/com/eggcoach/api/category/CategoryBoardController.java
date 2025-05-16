package com.eggcoach.api.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eggcoach.api.category.service.CategoryBoardService;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.JsonResponse;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.board.dto.CategoryDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.security.interceptor.annotation.LoginUserCheck;
import com.eggcoach.core.security.resolver.annotation.CurrentUserData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Category API", description = "게시판 카테고리 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board/category")
public class CategoryBoardController {

	private final CategoryBoardService categoryBoardService;

	@Operation(summary = "카테고리를 등록합니다.", description = "게시판 카테고리를 등록합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 등록되었습니다."),
		@ApiResponse(responseCode = "400", description = "등록에 실패했습니다."),
		@ApiResponse(responseCode = "403", description = "카테고리 등록 권한이 없습니다.")
	})
	@LoginUserCheck
	@PostMapping("/register")
	public JsonResponse<Void> registerCategory(
		@CurrentUserData CustomPrincipal customPrincipal,
		@RequestBody CategoryDto categoryDto
	) {
		ResultCode resultCode = categoryBoardService.registerCategory(categoryDto, customPrincipal);

		return JsonResponse.of(resultCode.getHttpStatus(), resultCode.getMessage());
	}

	@Operation(summary = "카테고리 목록을 조회합니다.", description = "모든 게시판 카테고리 목록을 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다.")
	})
	@GetMapping("/list")
	public JsonResponse<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> categories = categoryBoardService.getAllCategories();

		return JsonResponse.of(
			String.valueOf(HttpStatus.OK.value()),
			CommonCode.COMMON_SUCCESS_CODE.getMessage(),
			categories
		);
	}

	@Operation(summary = "카테고리 목록을 페이징 처리하여 조회합니다.", description = "게시판 카테고리 목록을 페이징 처리하여 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다.")
	})
	@GetMapping("/page")
	public JsonResponse<Page<CategoryDto>> getCategoriesByPage(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "displayOrder") String sort,
		@RequestParam(defaultValue = "asc") String direction
	) {
		Pageable pageable = PageRequest.of(
			page,
			size,
			Sort.Direction.fromString(direction),
			sort
		);

		Page<CategoryDto> categories = categoryBoardService.getAllCategories(pageable);

		return JsonResponse.of(
			String.valueOf(HttpStatus.OK.value()),
			CommonCode.COMMON_SUCCESS_CODE.getMessage(),
			categories
		);
	}

	@Operation(summary = "카테고리 상세 정보를 조회합니다.", description = "카테고리 ID로 카테고리 상세 정보를 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
		@ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.")
	})
	@GetMapping("/detail")
	public JsonResponse<CategoryDto> getCategoryById(@RequestParam Long categoryId) {
		try {
			CategoryDto categoryDto = categoryBoardService.getCategoryById(categoryId);

			return JsonResponse.of(
				String.valueOf(HttpStatus.OK.value()),
				CommonCode.COMMON_SUCCESS_CODE.getMessage(),
				categoryDto
			);
		} catch (Exception e) {
			return JsonResponse.of(
				String.valueOf(HttpStatus.NOT_FOUND.value()),
				"카테고리를 찾을 수 없습니다.",
				null
			);
		}
	}

	@Operation(summary = "카테고리를 수정합니다.", description = "게시판 카테고리 정보를 수정합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 수정되었습니다."),
		@ApiResponse(responseCode = "403", description = "카테고리 수정 권한이 없습니다."),
		@ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.")
	})
	@LoginUserCheck
	@PutMapping("/update")
	public JsonResponse<Void> updateCategory(
		@CurrentUserData CustomPrincipal customPrincipal,
		@RequestBody CategoryDto categoryDto
	) {
		ResultCode resultCode = categoryBoardService.updateCategory(categoryDto, customPrincipal);

		return JsonResponse.of(resultCode.getHttpStatus(), resultCode.getMessage());
	}

	@Operation(summary = "카테고리를 삭제합니다.", description = "게시판 카테고리를 삭제합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 삭제되었습니다."),
		@ApiResponse(responseCode = "403", description = "카테고리 삭제 권한이 없습니다."),
		@ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.")
	})
	@LoginUserCheck
	@DeleteMapping("/delete")
	public JsonResponse<Void> deleteCategory(
		@CurrentUserData CustomPrincipal customPrincipal,
		@RequestParam Long categoryId
	) {
		ResultCode resultCode = categoryBoardService.deleteCategory(categoryId, customPrincipal);

		return JsonResponse.of(resultCode.getHttpStatus(), resultCode.getMessage());
	}
}