package com.eggcoach.infrastructure.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eggcoach.infrastructure.account.entity.UserScheduleEntity;

public interface UserScheduleRepository extends JpaRepository<UserScheduleEntity, Long> {
}
