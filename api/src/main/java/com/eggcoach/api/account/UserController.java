package com.eggcoach.api.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eggcoach.api.account.service.AccountService;
import com.eggcoach.core.common.common.CommonCode;
import com.eggcoach.core.common.response.JsonResponse;
import com.eggcoach.core.common.response.ResultCode;
import com.eggcoach.core.domain.account.dto.SignUpDto;
import com.eggcoach.core.domain.account.dto.UserScheduleRequestDto;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.security.interceptor.annotation.LoginUserCheck;
import com.eggcoach.core.security.resolver.annotation.CurrentUserData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User API", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final AccountService accountService;

	private final com.eggcoach.core.security.util.SessionInfoPrinter sessionInfoPrinter;

	@LoginUserCheck
	@GetMapping("/home")
	public JsonResponse<CustomPrincipal> home(@CurrentUserData CustomPrincipal customPrincipal) throws Exception {
		sessionInfoPrinter.printSessionInformation();
		return JsonResponse.of(String.valueOf(HttpStatus.OK), "Test", customPrincipal);
	}

	@Operation(summary = "회원가입", description = "회원 정보를 받아서 가입합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 가입되었습니다."),
		@ApiResponse(responseCode = "400", description = "가입에 실패 했습니다.")
	})
	@PostMapping("/signup")
	public JsonResponse<Void> signup(
		@RequestPart("signUpDto") SignUpDto signUpDto,
		@RequestPart("userScheduleRequestDto") UserScheduleRequestDto userScheduleRequestDto,
		@RequestPart(value = "image", required = false) MultipartFile profileImg
	) throws Exception {
		ResultCode resultCode = accountService.signUp(signUpDto, userScheduleRequestDto, profileImg);

		return JsonResponse.of(resultCode.getHttpStatus(), resultCode.getMessage());
	}

	@Operation(summary = "유저정보를 가져옵니다", description = "세션으로 유저 정보 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공적으로 가입되었습니다."),
		@ApiResponse(responseCode = "400", description = "가입에 실패 했습니다.")
	})
	@LoginUserCheck
	@GetMapping("/userInfo")
	public JsonResponse<CustomPrincipal> getUserInfo(@CurrentUserData CustomPrincipal customPrincipal) {

		return JsonResponse.of(String.valueOf(HttpStatus.OK.value()), CommonCode.COMMON_SUCCESS_CODE.getMessage(), customPrincipal);
	}
}
