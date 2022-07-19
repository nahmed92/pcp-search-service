package com.deltadental.pcp.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deltadental.pcp.search.constants.PCPSearchServiceConstants;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.service.PCPSearchService;
import com.deltadental.pcp.search.validator.ProvidersRequestValidator;
import com.deltadental.platform.common.exception.ServiceException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SearchProvidersController {

	private static final String SUCCESS = "Success";
	
	@Autowired
	private PCPSearchService pcpSearchService;

	@Autowired
	private ProvidersRequestValidator requestValidator;

	@ApiOperation(value = PCPSearchServiceConstants.SUMMARY_PROVIDERS, notes = PCPSearchServiceConstants.SUMMARY_PROVIDERS_NOTES, response = ProvidersResponse.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully retrived providers", response = ProvidersResponse.class),
			@ApiResponse(code = 400, message = "Bad Request (validation error)", response = ServiceException.class),
			@ApiResponse(code = 404, message = "Unable to find providers.", response = ServiceException.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = ServiceException.class) })
	@ResponseBody
	@PostMapping(value = "/pcp/search/providers/_search", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProvidersResponse> searchProviders(@RequestBody ProvidersRequest providersRequest) throws ServiceException {
		log.info("START SearchProvidersController.searchProviders");
		requestValidator.validateProvidersRequest(providersRequest);
		ProvidersResponse providersResponse = pcpSearchService.searchProviders(providersRequest);
		log.info("END SearchProvidersController.searchProviders");		
		return getHttpStatus(providersResponse);
	}
	
	@ResponseBody
	@PostMapping(value = "/pcp-search/providers/_search", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	public ResponseEntity<ProvidersResponse> searchProvidersOld(@RequestBody ProvidersRequest providersRequest) throws ServiceException {
		requestValidator.validateProvidersRequest(providersRequest);
		return getHttpStatus(pcpSearchService.searchProviders(providersRequest));
	}

	private ResponseEntity<ProvidersResponse> getHttpStatus(ProvidersResponse providersResponse) {
		HttpStatus httpStatus = HttpStatus.OK;
		if(providersResponse == null || providersResponse.getPcpAssignmentResponse() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if(!SUCCESS.equalsIgnoreCase(providersResponse.getPcpAssignmentResponse().getProcessStatusCode())) { 
			httpStatus = HttpStatus.BAD_REQUEST;			
		}
		return new ResponseEntity<>(providersResponse, httpStatus);
	}
}
