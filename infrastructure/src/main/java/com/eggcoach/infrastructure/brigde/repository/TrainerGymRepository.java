package com.eggcoach.infrastructure.brigde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eggcoach.infrastructure.brigde.entity.TrainerGymEntity;

@Repository
public interface TrainerGymRepository extends JpaRepository<TrainerGymEntity, Long> {

	@Query("SELECT tg FROM TrainerGymEntity tg JOIN FETCH tg.userEntity WHERE tg.gymEntity.gym_seq = :gymSeq")
	List<TrainerGymEntity> findAllByGymSeqWithUser(@Param("gymSeq") Long gymSeq);

}
