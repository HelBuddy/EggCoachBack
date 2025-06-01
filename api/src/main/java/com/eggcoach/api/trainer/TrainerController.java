package com.eggcoach.api.trainer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eggcoach.api.trainer.service.TrainerService;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.JsonResponse;
import com.eggcoach.core.domain.bridge.dto.RegisterReviewDto;
import com.eggcoach.core.domain.bridge.dto.TrainerUserReviewDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.domain.trainer.dto.TrainerDetailDto;
import com.eggcoach.core.domain.trainer.dto.TrainerQnaViewDto;
import com.eggcoach.core.security.interceptor.annotation.LoginUserCheck;
import com.eggcoach.core.security.resolver.annotation.CurrentUserData;
import com.eggcoach.infrastructure.trainer.entity.TrainerQnaEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Trainer API", description = "트레이너 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {

	private final TrainerService trainerService;

	@Operation(summary = "트레이너 QnA 등록", description = "트레이너 QnA 등록합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 등록되었습니다."),
		@ApiResponse(responseCode = "400", description = "등록에 실패했습니다.")
	})
	@LoginUserCheck
	@PostMapping("/qna/register")
	public JsonResponse<Void> registerReview(
		@CurrentUserData CustomPrincipal customPrincipal,
		@RequestBody TrainerQnaViewDto trainerQnaViewDto
	) {
		//todo 실패시 처리는 나중 테스트기간
		trainerService.registerTrainerQna(trainerQnaViewDto, customPrincipal);

		return JsonResponse.of(String.valueOf(HttpStatus.OK.value()), CommonCode.COMMON_SUCCESS_CODE.getMessage());
	}

	@Operation(summary = "트레이너 QnA 목록을 가져옵니다..", description = "트레이너 QnA목록을 가져옵니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
		@ApiResponse(responseCode = "400", description = "조회에 실패했습니다.")
	})
	@GetMapping("/getAllTrainerQna")
	public JsonResponse<List<TrainerQnaEntity>> getAllTrainerQna(@RequestParam Long trainerId) {

		List<TrainerQnaEntity> allTrainerQna = trainerService.getAllTrainerQna(trainerId);

		return JsonResponse.of(String.valueOf(HttpStatus.OK.value()), CommonCode.COMMON_SUCCESS_CODE.getMessage(), allTrainerQna);
	}

	@Operation(summary = "트레이너 상세 정보를 가져옵니다.", description = "트레이너 상세 정보를 가져옵니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
		@ApiResponse(responseCode = "400", description = "조회에 실패했습니다.")
	})
	@GetMapping("/getDetail")
	public JsonResponse<TrainerDetailDto> getTrainerDetail(
		@RequestParam Long trainerId,
		@CurrentUserData CustomPrincipal customPrincipal) {

		TrainerDetailDto trainerDetail = trainerService.getTrainerDetail(trainerId, customPrincipal);

		return JsonResponse.of(String.valueOf(HttpStatus.OK.value()), CommonCode.COMMON_SUCCESS_CODE.getMessage(), trainerDetail);
	}
}
