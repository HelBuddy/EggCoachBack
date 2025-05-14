package com.eggcoach.infrastructure.gym.entity;

import com.eggcoach.core.domain.gym.dto.SignUpGymMakerDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "gym")
public class GymEntity {

	// PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gym_seq;

	// 헬스장 이름
	@Column(name = "gym_name")
	private String gym_name;

	// 경도
	@Column(name = "lng")
	private Double lng;

	// 위도
	@Column(name = "lat")
	private Double lat;

	// 주소
	@Column(name = "address")
	private String address;

	// 시
	@Column(name = "depth1")
	private String depth1;

	// 구
	@Column(name = "depth2")
	private String depth2;

	// 동
	@Column(name = "depth3")
	private String depth3;

	public GymEntity signupGymMarker(SignUpGymMakerDto signUpGymMakerDto) {
		this.gym_name = signUpGymMakerDto.getGymName();
		this.address = signUpGymMakerDto.getAddress();
		this.lng = signUpGymMakerDto.getLng();
		this.lat = signUpGymMakerDto.getLat();
		this.depth1 = signUpGymMakerDto.getDepth1();
		this.depth2 = signUpGymMakerDto.getDepth2();
		this.depth3 = signUpGymMakerDto.getDepth3();

		return this;
	}
}
