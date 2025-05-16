package com.eggcoach.infrastructure.brigde.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.bridge.dto.RegisterReviewDto;
import com.eggcoach.core.domain.bridge.dto.TrainerUserReviewDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.brigde.entity.TrainerUserReviewEntity;
import com.eggcoach.infrastructure.brigde.repository.TrainerUserReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerUserReviewService {

	private final TrainerUserReviewRepository trainerUserReviewRepository;

	public Double getTrainerAverageRating(Long trainerSeq) {
		Double avgRating = trainerUserReviewRepository.getAverageRatingByTrainerId(trainerSeq);
		return avgRating != null ? avgRating : 0.0;
	}

	public List<TrainerUserReviewDto> getUserReviews(Long userSeq) {
		List<TrainerUserReviewEntity> reviews = trainerUserReviewRepository.findAllByUserEntity_UserSeq(userSeq);

		return reviews.stream()
			.map(item ->
				new TrainerUserReviewDto(
					item.getTrainerUserReviewSeq(),
					item.getUserEntity().getUserSeq(),
					item.getTrainerEntity().getUserSeq(),
					item.getUserEntity().getNickName(),
					item.getRating(),
					null,
					item.getContent(),
					item.getCreateAt(),
					item.getUpdateAt()
				)
			)
			.collect(Collectors.toList());
	}

	public List<TrainerUserReviewDto> getTrainerReviews(Long trainerSeq) {
		List<TrainerUserReviewEntity> reviews = trainerUserReviewRepository.findAllByTrainerEntity_UserSeq(trainerSeq);

		return reviews.stream()
			.map(item ->
				new TrainerUserReviewDto(
					item.getTrainerUserReviewSeq(),
					item.getUserEntity().getUserSeq(),
					item.getTrainerEntity().getUserSeq(),
					item.getUserEntity().getNickName(),
					null,
					item.getRating(),
					item.getContent(),
					item.getCreateAt(),
					item.getUpdateAt()
				)
			)
			.collect(Collectors.toList());
	}

	public TrainerUserReviewEntity registerReview(UserEntity userEntity,
		UserEntity trainerEntity, RegisterReviewDto registerReviewDto) {

		return trainerUserReviewRepository.save(
			new TrainerUserReviewEntity().registerReview(userEntity, trainerEntity, registerReviewDto));
	}
}

