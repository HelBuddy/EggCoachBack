package com.eggcoach.api.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eggcoach.core.common.board.BoardCommonCode;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.board.dto.BoardDaoDto;
import com.eggcoach.core.domain.board.dto.BoardViewDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.service.UserServiceImpl;
import com.eggcoach.infrastructure.board.entity.BoardEntity;
import com.eggcoach.infrastructure.board.service.BoardInfraService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardInfraService boardInfraService;

	private final UserServiceImpl userServiceImpl;

	@Override
	public ResultCode registerBoard(BoardViewDto boardViewDto, CustomPrincipal customPrincipal) {
		UserEntity userEntity = userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail()).orElseThrow();
		boardInfraService.registerBoard(boardViewDto, userEntity);

		return ResultCode.builder()
			.httpStatus(String.valueOf(HttpStatus.OK.value()))
			.code(CommonCode.COMMON_SUCCESS_CODE.getCode())
			.message(CommonCode.COMMON_SUCCESS_CODE.getMessage())
			.build();
	}

	@Override
	public BoardDaoDto getBoardById(Long boardSeq) {
		BoardEntity boardEntity = boardInfraService.getBoardById(boardSeq);
		return new BoardDaoDto(
			boardEntity.getBoardSeq(),
			boardEntity.getCategoryBoardEntity().getCategoryBoardSeq(),
			boardEntity.getUserEntity().getUserSeq(),
			boardEntity.getCategoryName(),
			boardEntity.getLockYn(),
			boardEntity.getNoticeYn(),
			boardEntity.getLockPassword(),
			boardEntity.getTitle(),
			boardEntity.getContent(),
			boardEntity.getViewCnt(),
			boardEntity.getFavoriteCnt(),
			boardEntity.getCreateAt(),
			boardEntity.getUpdateAt()
		);
	}

	@Override
	public Page<BoardDaoDto> getAllBoardByUser(Long userId, Pageable pageable) {
		Page<BoardEntity> boardPage = boardInfraService.getAllBoardByUser(userId, pageable);

		return boardPage.map(item ->
			new BoardDaoDto(
				item.getBoardSeq(),
				item.getCategoryBoardEntity().getCategoryBoardSeq(),
				item.getUserEntity().getUserSeq(),
				item.getCategoryName(),
				item.getLockYn(),
				item.getNoticeYn(),
				item.getLockPassword(),
				item.getTitle(),
				item.getContent(),
				item.getViewCnt(),
				item.getFavoriteCnt(),
				item.getCreateAt(),
				item.getUpdateAt()
			)
		);
	}

	@Override
	public Page<BoardDaoDto> getAllBoardByCategory(Long categoryId, Pageable pageable) {
		Page<BoardEntity> boardPage = boardInfraService.getAllBoardByCategory(categoryId, pageable);

		return boardPage.map(item ->
			new BoardDaoDto(
				item.getBoardSeq(),
				item.getCategoryBoardEntity().getCategoryBoardSeq(),
				item.getUserEntity().getUserSeq(),
				item.getCategoryName(),
				item.getLockYn(),
				item.getNoticeYn(),
				item.getLockPassword(),
				item.getTitle(),
				item.getContent(),
				item.getViewCnt(),
				item.getFavoriteCnt(),
				item.getCreateAt(),
				item.getUpdateAt()
			)
		);
	}

	@Override
	public ResultCode deleteBoardById(Long boardSeq, CustomPrincipal customPrincipal) {

		BoardEntity boardEntity;
		try {
			boardEntity = boardInfraService.getBoardById(boardSeq);
		} catch (Exception e) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.NOT_FOUND.value()))
				.code(BoardCommonCode.BOARD_NOT_FOUND.getCode())
				.message(BoardCommonCode.BOARD_NOT_FOUND.getMessage())
				.build();
		}

		UserEntity userEntity = userServiceImpl.getUserEntityByUserEmail(customPrincipal.getEmail()).orElseThrow();

		if (!boardEntity.getUserEntity().getUserSeq().equals(userEntity.getUserSeq())) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.FORBIDDEN.value()))
				.code(BoardCommonCode.PERMISSION_DENIED.getCode())
				.message(BoardCommonCode.PERMISSION_DENIED.getMessage())
				.build();
		}

		try {
			boardInfraService.deleteBoardById(boardSeq);
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.OK.value()))
				.code(BoardCommonCode.DELETED_SUCCESS.getCode())
				.message(BoardCommonCode.DELETED_SUCCESS.getMessage())
				.build();
		} catch (Exception e) {
			return ResultCode.builder()
				.httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.code(BoardCommonCode.DELETE_FAILED.getCode())
				.message(BoardCommonCode.DELETE_FAILED.getMessage())
				.build();
		}
	}

}
