package com.eggcoach.api.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.board.dto.BoardDaoDto;
import com.eggcoach.core.domain.board.dto.BoardViewDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;

public interface BoardService {

	ResultCode registerBoard(BoardViewDto boardViewDto, CustomPrincipal customPrincipal);

	BoardDaoDto getBoardById(Long boardSeq);

	Page<BoardDaoDto> getAllBoardByUser(Long userId, Pageable pageable);

	Page<BoardDaoDto> getAllBoardByCategory(Long categoryId, Pageable pageable);

	ResultCode deleteBoardById(Long boardSeq, CustomPrincipal customPrincipal);

}
