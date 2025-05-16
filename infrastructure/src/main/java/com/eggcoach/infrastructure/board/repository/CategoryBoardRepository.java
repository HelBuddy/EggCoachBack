package com.eggcoach.infrastructure.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eggcoach.infrastructure.board.entity.CategoryBoardEntity;

public interface CategoryBoardRepository extends JpaRepository<CategoryBoardEntity, Long> {

	Optional<CategoryBoardEntity> findByCategoryBoardSeq(Long categoryBoardSeq);
}
