package com.deltadental.pcp.search.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deltadental.pcp.search.constants.PCPSearchServiceConstants;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.service.PCPSearchService;
import com.deltadental.pcp.search.service.impl.ProvidersAuditService;
import com.deltadental.platform.common.annotation.aop.MethodExecutionTime;
import com.deltadental.platform.common.exception.ServiceError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/pcp-search", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(description = "Endpoint for searching providers.", value = "/pcp-search")
@Slf4j
@Validated
public class PCPSearchProvidersController {

	@Autowired
	private PCPSearchService pcpSearchService;

	@Autowired
	private ProvidersAuditService providersAuditService;

	@Autowired
	private ProvidersRequestValidator requestValidator;
	
	@ApiOperation(value = PCPSearchServiceConstants.SUMMARY_PROVIDERS, notes = PCPSearchServiceConstants.SUMMARY_PROVIDERS_NOTES, response = PCPAssignmentResponse.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully retrived providers", response = PCPAssignmentResponse.class),
			@ApiResponse(code = 400, message = "Bad Request (validation error)", response = ServiceError.class),
			@ApiResponse(code = 404, message = "Unable to find providers.", response = ServiceError.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
	@PostMapping(value = "/providers/_search", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProvidersResponse> searchProviders(@Valid @RequestBody ProvidersRequest providersRequest) {
		log.info("START PCPSearchProvidersController.searchProviders");
//		requestValidator.validateProvidersRequest(providersRequest);
		try {
			ProvidersResponse providersResponse = pcpSearchService.providers(providersRequest);
			providersAuditService.save(providersRequest, providersResponse);
			log.info("END PCPSearchProvidersController.searchProviders");
			ResponseEntity<ProvidersResponse> responseEntity = new ResponseEntity<>(providersResponse, HttpStatus.OK);
			return responseEntity;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getMessage());
		}
	}

}
