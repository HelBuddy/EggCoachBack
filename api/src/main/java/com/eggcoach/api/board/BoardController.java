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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eggcoach.api.board.service.BoardService;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.JsonResponse;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.board.dto.BoardDaoDto;
import com.eggcoach.core.domain.board.dto.BoardViewDto;
import com.eggcoach.core.domain.bridge.dto.TrainerUserReviewDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.security.interceptor.annotation.LoginUserCheck;
import com.eggcoach.core.security.resolver.annotation.CurrentUserData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Board API", description = "게시판 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	@Operation(summary = "게시글을 등록합니다.", description = "게시글을 등록합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 등록되었습니다."),
		@ApiResponse(responseCode = "400", description = "등록에 실패했습니다.")
	})
	@LoginUserCheck
	@PostMapping("/register")
	public JsonResponse<Void> registerBoard(
		@CurrentUserData CustomPrincipal customPrincipal,
		@RequestBody BoardViewDto boardViewDto
	) {
		ResultCode resultCode = boardService.registerBoard(boardViewDto, customPrincipal);

		return JsonResponse.of(resultCode.getHttpStatus(), resultCode.getMessage());
	}

	@Operation(summary = "게시글 상세 정보를 가져옵니다.", description = "게시글 상세 정보를 가져옵니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
		@ApiResponse(responseCode = "400", description = "조회에 실패했습니다.")
	})
	@GetMapping("/read")
	public JsonResponse<BoardDaoDto> getBoardByBoardId(@RequestParam Long boardId) {

		BoardDaoDto boardDaoDto = boardService.getBoardById(boardId);

		return JsonResponse.of(String.valueOf(HttpStatus.OK.value()), CommonCode.COMMON_SUCCESS_CODE.getMessage(), boardDaoDto);
	}

	@Operation(summary = "유저가 작성한 게시글 목록을 가져옵니다.",
		description = "유저가 작성한 게시글 목록을 페이징 처리하여 가져옵니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
		@ApiResponse(responseCode = "400", description = "조회에 실패했습니다.")
	})
	@GetMapping("/user/read")
	public JsonResponse<Page<BoardDaoDto>> getAllBoardByUserId(
		@RequestParam Long userId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "createAt") String sort,
		@RequestParam(defaultValue = "desc") String direction
	) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);

		Page<BoardDaoDto> boardPage = boardService.getAllBoardByUser(userId, pageable);

		return JsonResponse.of(
			String.valueOf(HttpStatus.OK.value()),
			CommonCode.COMMON_SUCCESS_CODE.getMessage(),
			boardPage
		);
	}

	@Operation(summary = "카테고리별 게시글 목록을 가져옵니다.",
		description = "카테고리별 게시글 목록을 페이징 처리하여 가져옵니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
		@ApiResponse(responseCode = "400", description = "조회에 실패했습니다.")
	})
	@GetMapping("/category/read")
	public JsonResponse<Page<BoardDaoDto>> getAllBoardByCategory(
		@RequestParam Long categoryId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "createAt") String sort,
		@RequestParam(defaultValue = "desc") String direction
	) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);

		Page<BoardDaoDto> boardPage = boardService.getAllBoardByCategory(categoryId, pageable);

		return JsonResponse.of(
			String.valueOf(HttpStatus.OK.value()),
			CommonCode.COMMON_SUCCESS_CODE.getMessage(),
			boardPage
		);
	}

	@Operation(summary = "게시글을 삭제합니다.", description = "게시글 ID를 이용하여 해당 게시글을 삭제합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 삭제되었습니다."),
		@ApiResponse(responseCode = "403", description = "삭제 권한이 없습니다."),
		@ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없습니다."),
		@ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
	})
	@LoginUserCheck
	@DeleteMapping("/delete")
	public JsonResponse<Void> deleteBoard(
		@CurrentUserData CustomPrincipal customPrincipal,
		@RequestParam Long boardId
	) {
		ResultCode resultCode = boardService.deleteBoardById(boardId, customPrincipal);

		return JsonResponse.of(resultCode.getHttpStatus(), resultCode.getMessage());
	}
}
