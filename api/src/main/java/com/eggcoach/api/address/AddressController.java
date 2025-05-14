package com.eggcoach.api.address;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.eggcoach.api.address.service.AddressService;
import com.eggcoach.core.common.response.JsonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Address API", description = "주소 관련 API")
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;


	@Operation(summary = "주소 검색", description = "주소 정보를 받아서 관련 하위 주소를 출력합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "주소정보 로드에 성공 했습니다."),
		@ApiResponse(responseCode = "400", description = "주소정보 로드에 실패 했습니다.")
	})
	@GetMapping("/search")
	public JsonResponse<JsonNode> searchAddress(@RequestParam String query) throws JsonProcessingException {

		ResponseEntity<JsonNode> response = addressService.searchAddress(query);


		return JsonResponse.of(
			response.getStatusCode().toString(), response.getStatusCode().toString(), response.getBody());
	}

}