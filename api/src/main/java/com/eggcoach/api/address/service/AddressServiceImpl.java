package com.eggcoach.api.address.service;

import static com.eggcoach.core.common.external.AddressURI.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AddressServiceImpl implements AddressService {

	@Value("${external.api.kakaomap}")
	private String kakaoMapApiKey;

	@Override
	public ResponseEntity<JsonNode> searchAddress(String query) throws JsonProcessingException {
		String apiKey = "KakaoAK "+ kakaoMapApiKey;
		String url = UriComponentsBuilder
			.fromHttpUrl(KAKAO_MAP_REQUEST_URL.getUri())
			.queryParam("query", query)
			.build()
			.toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", apiKey);
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
			url, HttpMethod.GET, entity, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode body = objectMapper.readTree(response.getBody());

		return ResponseEntity.status(response.getStatusCode())
			.body(body);
	}
}
