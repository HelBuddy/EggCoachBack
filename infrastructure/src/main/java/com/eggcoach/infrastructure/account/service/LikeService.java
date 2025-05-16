package com.eggcoach.infrastructure.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.account.dto.LikeDto;
import com.eggcoach.infrastructure.account.entity.LikeEntity;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.account.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {

	private final LikeRepository likeRepository;

	public List<LikeDto> getAllUserLike(Long userSeq) {
		return likeRepository.findAllByUserEntity_UserSeq(userSeq)
			.stream().map(item ->
				new LikeDto(item.getLikeSeq(),item.getTrainerId())).toList();
	}

	public void registerLike(UserEntity userEntity, LikeDto likeDto) {

		Optional<LikeEntity> likeEntity = likeRepository.findByTrainerId(likeDto.getTrainerSeq());

		if (likeEntity.isEmpty()) {
			likeRepository.save(new LikeEntity().registerLike(userEntity, likeDto));
		} else {
			likeRepository.delete(likeEntity.get());
		}
	}
}
