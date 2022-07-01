package com.deltadental.pcp.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deltadental.pcp.search.domain.Facility;
import com.deltadental.pcp.search.service.PCPSearchService;
import com.deltadental.platform.common.annotation.aop.MethodExecutionTime;
import com.deltadental.platform.common.exception.ServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/pcp-search", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(value = "/pcp-search")
@Slf4j
public class FacilitySearchController {

	@Autowired
	private PCPSearchService pcpSearchService;

	@ApiOperation(value = "Retrive Facility", notes = "This API retrive the facility information.", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrived providers", response = Facility.class),
			@ApiResponse(code = 400, message = "Bad Request (validation error)", response = ServiceException.class),
			@ApiResponse(code = 404, message = "Facility Not Found.", response = ServiceException.class),
			@ApiResponse(code = 500, message = "Unknown Exception.", response = ServiceException.class) })
	@ResponseBody
	@MethodExecutionTime
	@GetMapping(value = "/facility/{facility-id}")
	public ResponseEntity<Facility> facilitySearch(@PathVariable("facility-id") String facilityId) {
		log.info("START PCPSearchProvidersController.searchFacility");
		Facility response = pcpSearchService.searchFacility(facilityId);
		log.info("END PCPSearchProvidersController.searchFacility");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}