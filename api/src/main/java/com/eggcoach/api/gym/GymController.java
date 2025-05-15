package com.eggcoach.api.gym;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eggcoach.api.gym.service.GymService;
import com.eggcoach.core.common.account.AccountSuccessCode;
import com.eggcoach.core.common.gym.GymSuccessCode;
import com.eggcoach.core.common.response.JsonResponse;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.gym.dto.GymMarkerDto;
import com.eggcoach.core.domain.gym.dto.SignUpGymMakerDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.security.interceptor.annotation.LoginUserCheck;
import com.eggcoach.core.security.resolver.annotation.CurrentUserData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Gym API", description = "헬스장 관련 API")
@RestController
@RequestMapping("/api/gym")
@RequiredArgsConstructor
public class GymController {

	private final GymService gymService;

	@Operation(summary = "지도 영역에 해당되는 헬스장 조회", description = "현재 맵영역에 해당되는 헬스장 정보를 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "헬스장 정보 로드에 성공 했습니다."),
		@ApiResponse(responseCode = "400", description = "헬스장 정보 로드에 실패 했습니다.")
	})

	@GetMapping("/getAllGymBounds")
	public JsonResponse<List<GymMarkerDto>> getAllGymBounds(
		@RequestParam Double minX,
		@RequestParam Double minY,
		@RequestParam Double maxX,
		@RequestParam Double maxY
	) {
		List<GymMarkerDto> allGymBounds = gymService.getAllGymBounds(minX, maxX, minY, maxY);

		return JsonResponse.of(
			String.valueOf(HttpStatus.OK), GymSuccessCode.GET_BOUNDS_LIST_SUCCESS_CODE.getCode(), allGymBounds);
	}

	@Operation(summary = "신규 헬스장 등록", description = "신규 헬스장을 등록합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "헬스장 정보 등록에 성공 했습니다."),
		@ApiResponse(responseCode = "400", description = "헬스장 정보 등록에 실패 했습니다.")
	})
	@PostMapping("/signupGym")
	public JsonResponse<Void> signupGym(@RequestBody SignUpGymMakerDto signUpGymMakerDto) {

		ResultCode resultCode = gymService.registerGym(signUpGymMakerDto);

		return JsonResponse.of(resultCode.getHttpStatus(), resultCode.getMessage());
	}

	@Operation(summary = "헬스장에 트레이너 등록", description = "트레이너를 헬스장에 등록합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "정보 등록에 성공 했습니다."),
		@ApiResponse(responseCode = "400", description = "정보 등록에 실패 했습니다.")
	})
	@LoginUserCheck
	@PostMapping("/assignTrainerAtGym")
	public JsonResponse<Void> setTrainerAtGym(
		@CurrentUserData CustomPrincipal customPrincipal,
		@RequestBody GymMarkerDto gymMarkerDto) {

		ResultCode resultCode = gymService.setTrainerAtGym(customPrincipal, gymMarkerDto);

		return JsonResponse.of(resultCode.getCode(), resultCode.getMessage());
	}
}
