package com.eggcoach.core.domain.account.model;

import java.time.LocalDateTime;

import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.core.common.account.UserStatus;
import com.eggcoach.core.common.account.UserType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

	// PK
	private Long userSeq;

	// 비밀번호
	private String password;

	// 이름
	private String userName;

	// 이메일
	private String userEmail;

	// 성별
	private String gender;

	// 상태
	private UserStatus status;

	// 회원구분
	private UserType userType;

	// 닉네임
	private String nickName;

	// 주소
	private String address;

	// 휴대폰번호
	private String phoneNumber;

	// 프로필사진
	private String profile;

	// 생성 날짜
	private LocalDateTime createAt;

	// 수정 날짜
	private LocalDateTime updateAt;

	// 신고 카운트
	private Integer reportCnt;

	// 체중
	private Double weight;

	// 근골격량
	private Double muscle;

	// 체지방량
	private Double bodyFat;

	// 키
	private Double height;

	// 소셜로그인
	private OAuthVendor socialProvider;
}
