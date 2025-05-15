package com.eggcoach.infrastructure.gym.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.eggcoach.core.domain.gym.dto.SignUpGymMakerDto;
import com.eggcoach.infrastructure.brigde.entity.TrainerGymEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	// 생성 날짜
	@Column(name = "create_at")
	private LocalDateTime createAt;

	// 수정 날짜
	@Column(name = "update_at")
	private LocalDateTime updateAt;

	// 헬스회원 일대다
	@OneToMany(mappedBy = "trainerGymSeq", fetch = FetchType.LAZY)
	private List<TrainerGymEntity> trainerGymEntities = new ArrayList<>();


	public GymEntity signupGymMarker(SignUpGymMakerDto signUpGymMakerDto) {
		this.gym_name = signUpGymMakerDto.getGymName();
		this.address = signUpGymMakerDto.getAddress();
		this.lng = signUpGymMakerDto.getLng();
		this.lat = signUpGymMakerDto.getLat();
		this.depth1 = signUpGymMakerDto.getDepth1();
		this.depth2 = signUpGymMakerDto.getDepth2();
		this.depth3 = signUpGymMakerDto.getDepth3();
		this.createAt = LocalDateTime.now();
		this.updateAt = LocalDateTime.now();

		return this;
	}

	public void addGym(TrainerGymEntity trainerGymEntity) {
		this.trainerGymEntities.add(trainerGymEntity);
	}
}
