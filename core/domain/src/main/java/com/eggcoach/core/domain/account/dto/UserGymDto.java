package com.eggcoach.core.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGymDto {

	// PK
	private Long userSeq;

	// 이름
	private String userName;

	// 성별
	private String gender;

	// 연령대
	private Integer age;
}
