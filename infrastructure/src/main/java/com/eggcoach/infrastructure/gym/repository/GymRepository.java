package com.eggcoach.infrastructure.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eggcoach.infrastructure.gym.entity.GymEntity;

@Repository
public interface GymRepository extends JpaRepository<GymEntity, Long> {

	@Query("SELECT g FROM GymEntity g WHERE g.lng BETWEEN :minX AND :maxX AND g.lat BETWEEN :minY AND :maxY")
	List<GymEntity> findAllByBounds(
		@Param("minX") Double minX,
		@Param("maxX") Double maxX,
		@Param("minY") Double minY,
		@Param("maxY") Double maxY);

}
