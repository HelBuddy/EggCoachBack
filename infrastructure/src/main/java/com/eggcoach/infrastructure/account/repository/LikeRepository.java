package com.eggcoach.infrastructure.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eggcoach.infrastructure.account.entity.LikeEntity;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

	List<LikeEntity> findAllByUserEntity_UserSeq(Long userId);

	Optional<LikeEntity> findByTrainerId(Long trainerId);

	boolean existsByUserEntity_UserSeqAndTrainerId(Long userSeq, Long trainerId);
}
