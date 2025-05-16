package com.eggcoach.infrastructure.brigde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eggcoach.infrastructure.brigde.entity.TrainerUserReviewEntity;

@Repository
public interface TrainerUserReviewRepository extends JpaRepository<TrainerUserReviewEntity, Long> {

	// 특정 트레이너의 모든 리뷰 조회
	List<TrainerUserReviewEntity> findAllByTrainerEntity_UserSeq(Long trainerId);

	// 특정 유저의 모든 리뷰 조회
	List<TrainerUserReviewEntity> findAllByUserEntity_UserSeq(Long userId);

	// 트레이너의 평균 평점 계산
	@Query("SELECT AVG(r.rating) FROM TrainerUserReviewEntity r WHERE r.trainerEntity.userSeq = :trainerSeq")
	Double getAverageRatingByTrainerId(@Param("trainerSeq") Long trainerSeq);

}
