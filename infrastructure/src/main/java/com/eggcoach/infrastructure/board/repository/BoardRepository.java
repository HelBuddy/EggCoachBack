package com.eggcoach.infrastructure.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eggcoach.infrastructure.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	Optional<BoardEntity> findByBoardSeq(Long boardSeq);

	// 페이징 처리된 메서드 추가
	Page<BoardEntity> findAllByCategoryBoardEntity_CategoryBoardSeq(Long categoryBoardEntityCategoryBoardSeq, Pageable pageable);


	Page<BoardEntity> findAllByUserEntity_UserSeq(Long userEntityUserSeq, Pageable pageable);

}
