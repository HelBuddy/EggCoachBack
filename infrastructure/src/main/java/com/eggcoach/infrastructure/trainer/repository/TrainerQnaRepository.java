package com.eggcoach.infrastructure.trainer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eggcoach.infrastructure.trainer.entity.TrainerQnaEntity;

@Repository
public interface TrainerQnaRepository extends JpaRepository<TrainerQnaEntity, Long> {
	List<TrainerQnaEntity> findAllByUserEntity_UserSeq(Long userSeq);
}
