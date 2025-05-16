package com.eggcoach.infrastructure.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.board.dto.BoardViewDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.board.entity.BoardEntity;
import com.eggcoach.infrastructure.board.entity.CategoryBoardEntity;
import com.eggcoach.infrastructure.board.repository.BoardRepository;
import com.eggcoach.infrastructure.board.repository.CategoryBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardInfraService {

	private final BoardRepository boardRepository;

	private final CategoryBoardRepository categoryBoardRepository;

	public void registerBoard(BoardViewDto boardViewDto, UserEntity userEntity) {

		CategoryBoardEntity categoryBoardEntity =
			categoryBoardRepository.findByCategoryBoardSeq(boardViewDto.getCategoryId()).orElseThrow();

		boardRepository.save(new BoardEntity().registerBoard(boardViewDto, categoryBoardEntity, userEntity));
	}

	public BoardEntity getBoardById(Long boardSeq) {
		return boardRepository.findByBoardSeq(boardSeq).orElseThrow();
	}

	public Page<BoardEntity> getAllBoardByUser(Long userId, Pageable pageable) {
		return boardRepository.findAllByUserEntity_UserSeq(userId, pageable);
	}

	public Page<BoardEntity> getAllBoardByCategory(Long categoryId, Pageable pageable) {
		return boardRepository.findAllByCategoryBoardEntity_CategoryBoardSeq(categoryId, pageable);
	}

	public void deleteBoardById(Long boardSeq) {
		boardRepository.deleteById(boardSeq);
	}

	// 게시글 존재여부 확인 메서드 (선택사항)
	public boolean existsBoard(Long boardSeq) {
		return boardRepository.existsById(boardSeq);
	}

	// 게시글 작성자 확인 메서드 (권한 체크용)
	public boolean isBoardOwnedByUser(Long boardSeq, Long userSeq) {
		Optional<BoardEntity> board = boardRepository.findByBoardSeq(boardSeq);
		return board.isPresent() && board.get().getUserEntity().getUserSeq().equals(userSeq);
	}

}
