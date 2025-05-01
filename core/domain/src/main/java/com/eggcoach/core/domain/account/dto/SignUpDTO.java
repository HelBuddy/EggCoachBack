package com.eggcoach.core.domain.account.dto;

import java.time.LocalDateTime;

import com.eggcoach.core.common.account.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {

	// 이름
	private String userName;

	// 프로파일
	private String profile;

	// 이메일
	private String userEmail;

	// 비밀번호
	private String password;

	// 성별
	private String gender;

	// 상태
	private String status;

	// 회원구분
	private String userType;

	// 닉네임
	private String nickName;

	// 주소
	private String address;

	// 휴대폰번호
	private String phoneNumber;

	// 체중
	private Double weight;

	// 근골격량
	private Double muscle;

	// 체지방량
	private Double bodyFat;

	// 키
	private Double height;

	// 로그인 타입 (소셜여부)
	private String socialProvider;
}
