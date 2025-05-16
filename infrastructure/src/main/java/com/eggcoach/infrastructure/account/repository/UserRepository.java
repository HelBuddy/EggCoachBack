package com.eggcoach.infrastructure.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eggcoach.infrastructure.account.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUserEmail(String email);

	Optional<UserEntity> findByUserSeq(Long userSeq);

	boolean existsByUserEmail(String email);
}