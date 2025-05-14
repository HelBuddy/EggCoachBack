package com.eggcoach.api.address.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface AddressService {

	ResponseEntity<JsonNode> searchAddress(String query) throws JsonProcessingException;
}
