package com.deltadental.pcp.search.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deltadental.pcp.search.constants.PCPSearchServiceConstants;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.service.PCPSearchService;
import com.deltadental.platform.common.exception.ServiceError;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class PCPValidateController {

	@Autowired(required = true)
	@Qualifier("pcpSearchService")
	private PCPSearchService pcpSearchService;

	@ApiOperation(value = PCPSearchServiceConstants.SUMMARY_PCPVALIDATE, notes = PCPSearchServiceConstants.SUMMARY_SUMMARY_PCPVALIDATE_NOTES, response = PCPAssignmentResponse.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully validated provider", response = PCPAssignmentResponse.class),
			@ApiResponse(code = 400, message = "Bad request", response = ServiceError.class),
			@ApiResponse(code = 404, message = "Unable to validate provider.", response = ServiceError.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@PostMapping(value = "/pcp/search/validate", consumes =  MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<PCPAssignmentResponse> validate(@Valid @RequestBody PCPValidateRequest pcpValidateRequest) {
		log.info("START PCPValidateController.validate");
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchService.validate(pcpValidateRequest);
		log.info("END PCPValidateController.validate");
		return new ResponseEntity<>(pcpAssignmentResponse,HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "/pcp-search/validate", consumes =  MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@Deprecated
	public ResponseEntity<PCPAssignmentResponse> validateOld(@Valid @RequestBody PCPValidateRequest pcpValidateRequest) {
		return new ResponseEntity<>(pcpSearchService.validate(pcpValidateRequest),HttpStatus.OK);
	}
}
