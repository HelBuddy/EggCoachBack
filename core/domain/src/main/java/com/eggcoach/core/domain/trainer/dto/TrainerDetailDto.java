package com.eggcoach.core.domain.trainer.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.core.common.account.UserStatus;
import com.eggcoach.core.common.account.UserType;
import com.eggcoach.core.domain.account.model.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrainerDetailDto {

	// PK
	private Long userSeq;

	// 이름
	private String userName;

	// 성별
	private String gender;

	// 상태
	private UserStatus status;

	// 회원구분
	private UserType userType;

	// 닉네임
	private String nickName;

	// 프로필사진
	private String profile;

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;

	// 소셜로그인
	private OAuthVendor socialProvider;

	// 연령대
	private Integer age;

	// 어필
	private String appeal;

	// 평점 편균 점수
	private Double trainerAverageRating;

	// 내가 좋아요 누른 여부 (로그인시)
	private String like;

	// 트레이너 가능시간 대
	private List<TrainerScheduleDto> trainerScheduleDtoList;
}
