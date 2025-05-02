package com.eggcoach.infrastructure.account.entity;

import java.time.LocalDateTime;

import com.eggcoach.core.common.account.UserStatus;
import com.eggcoach.core.common.account.OAuthVendor;
import com.eggcoach.core.common.account.UserType;
import com.eggcoach.core.domain.account.model.User;
import com.eggcoach.core.domain.account.dto.SignUpDto;
import com.eggcoach.infrastructure.converter.account.OAuthVendorConverter;
import com.eggcoach.infrastructure.converter.account.UserStatusConverter;
import com.eggcoach.infrastructure.converter.account.UserTypeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userSeq;

	// 비밀번호
	@Column(name = "password")
	private String password;

	// 이름
	@Column(name = "user_nm")
	private String userName;

	// 이메일
	@Column(name = "email")
	private String userEmail;

	// 성별
	@Column(name = "gender")
	private String gender;

	// 상태
	@Column(name = "status")
	@Convert(converter = UserStatusConverter.class)
	private UserStatus status;

	// 회원구분
	@Column(name = "user_type")
	@Convert(converter = UserTypeConverter.class)
	private UserType userType;

	// 닉네임
	@Column(name = "nickname")
	private String nickName;

	// 주소
	@Column(name = "address")
	private String address;

	// 휴대폰번호
	@Column(name = "phone_number")
	private String phoneNumber;

	// 프로필사진
	@Column(name = "profile")
	private String profile;

	// 생성 날짜
	@Column(name = "create_at")
	private LocalDateTime createAt;

	// 수정 날짜
	@Column(name = "update_at")
	private LocalDateTime updateAt;

	// 신고 카운트
	@Column(name = "report_cnt")
	private Integer reportCnt;

	// 체중
	@Column(name = "weight")
	private Double weight;

	// 근골격량
	@Column(name = "muscle")
	private Double muscle;

	// 체지방량
	@Column(name = "body_fat")
	private Double bodyFat;

	// 키
	@Column(name = "height")
	private Double height;

	// 소셜 로그인 구분
	@Column(name = "socialProvider")
	@Convert(converter = OAuthVendorConverter.class)
	private OAuthVendor socialProvider;

	// 연령대
	@Column(name = "age")
	private Integer age;

	public User toUser() {
		return User.builder()
			.userSeq(userSeq)
			.password(password)
			.userName(userName)
			.userEmail(userEmail)
			.gender(gender)
			.status(status)
			.userType(userType)
			.nickName(nickName)
			.address(address)
			.profile(profile)
			.createAt(createAt)
			.updateAt(updateAt)
			.reportCnt(reportCnt)
			.weight(weight)
			.muscle(muscle)
			.bodyFat(bodyFat)
			.height(height)
			.socialProvider(socialProvider)
			.age(age)
			.build();
	}

	public UserEntity signUp(SignUpDto signUpDTO) {
		this.userName = signUpDTO.getUserName();
		this.userEmail = signUpDTO.getUserEmail();
		this.password = signUpDTO.getPassword();
		this.gender = signUpDTO.getGender();
		this.status = UserStatus.ACTIVE;
		this.userType = UserType.fromCode(signUpDTO.getUserType());
		this.nickName = signUpDTO.getNickName();
		this.address = signUpDTO.getAddress();
		this.phoneNumber = signUpDTO.getPhoneNumber();
		this.createAt = LocalDateTime.now();
		this.weight = signUpDTO.getWeight();
		this.muscle = signUpDTO.getMuscle();
		this.bodyFat = signUpDTO.getBodyFat();
		this.height = signUpDTO.getHeight();
		this.socialProvider = signUpDTO.getSocialProvider() == null ? OAuthVendor.DEFAULT : OAuthVendor.fromCode(signUpDTO.getSocialProvider());
		this.age = signUpDTO.getAge();
		return this;
	}


	public void encodePassword(String password) {
		this.password = password;
	}

}
