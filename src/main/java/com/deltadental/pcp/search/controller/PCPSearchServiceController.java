package com.deltadental.pcp.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deltadental.pcp.search.constants.PCPSearchServiceConstants;
import com.deltadental.pcp.search.domain.BPBLResolutionResponse;
import com.deltadental.pcp.search.domain.BlResolutionResponse;
import com.deltadental.pcp.search.domain.BlServiceRequest;
import com.deltadental.pcp.search.domain.FacilityResponse;
import com.deltadental.pcp.search.domain.FacilitySearchRequest;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.RetrieveDistinctExceptionGroupsRes;
import com.deltadental.pcp.search.domain.StatePcpAssignmentRequest;
import com.deltadental.pcp.search.domain.StatePcpAssignmentResponse;
import com.deltadental.pcp.search.service.PCPSearchService;
import com.deltadental.platform.common.annotation.aop.MethodExecutionTime;
import com.deltadental.platform.common.exception.ServiceError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/pcp-search", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "/pcp-search")
@Slf4j
@Data
public class PCPSearchServiceController {

	@Autowired(required=true)
	@Qualifier("pcpSearchService")
	private PCPSearchService pcpSearchService;
	
	@ApiOperation(
			value = PCPSearchServiceConstants.SUMMARY_GET_PROVIDERS, 
			notes = PCPSearchServiceConstants.SUMMARY_GET_PROVIDERS_NOTES, 
			response = PCPAssignmentResponse.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrived providers.", response = PCPAssignmentResponse.class),
                    @ApiResponse(code = 400, message = "Bad request.", response = ServiceError.class),
//                    @ApiResponse(code = 403, message = "Unauthorized", response = ServiceError.class),
                    @ApiResponse(code = 404, message = "Unable to find providers.", response = ServiceError.class),
                    @ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
    @PostMapping(value = PCPSearchServiceConstants.GET_PROVIDERS_URI, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PCPAssignmentResponse> getProviders(@RequestBody PcpAssignmentRequest pcpAssignmentRequest) {
		log.info("START PCPSearchServiceController.getProviders");
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchService.getProviders(pcpAssignmentRequest);
		ResponseEntity<PCPAssignmentResponse> responseEntity = new ResponseEntity<>(pcpAssignmentResponse, HttpStatus.OK); 
		log.info("END PCPSearchServiceController.getProviders");
		return responseEntity;
	}

	@ApiOperation(
			value = PCPSearchServiceConstants.SUMMARY_FACILITY_SEARCH, 
			notes = PCPSearchServiceConstants.SUMMARY_FACILITY_SEARCH_NOTES, 
			response = FacilityResponse.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully found facility search data.", response = FacilityResponse.class),
                    @ApiResponse(code = 400, message = "Bad request.", response = ServiceError.class),
//                    @ApiResponse(code = 403, message = "Unauthorized", response = ServiceError.class),
                    @ApiResponse(code = 404, message = "Unable to find data for facility search.", response = ServiceError.class),
                    @ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
    @PostMapping(value = PCPSearchServiceConstants.GET_FACILITY_SEARCH_URI, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<FacilityResponse> facilitySearch(@RequestBody FacilitySearchRequest facilitySearchRequest) {
		log.info("START PCPSearchServiceController.facilitySearch");
		ResponseEntity<FacilityResponse> responseEntity = null;
		FacilityResponse facilityResponse = pcpSearchService.facilitySearch(facilitySearchRequest);
		if(facilityResponse.getFacility().isEmpty()) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<>(facilityResponse, HttpStatus.OK);
		}
		
		log.info("END PCPSearchServiceController.facilitySearch");
		return responseEntity;
	}

	@ApiOperation(
			value = PCPSearchServiceConstants.SUMMARY_BUSINESS_LEVELS, 
			notes = PCPSearchServiceConstants.SUMMARY_BUSINESS_LEVELS_NOTES, 
			response = BlResolutionResponse.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrived business levels.", response = BlResolutionResponse.class),
                    @ApiResponse(code = 400, message = "Bad request.", response = ServiceError.class),
//                    @ApiResponse(code = 403, message = "Unauthorized", response = ServiceError.class),
                    @ApiResponse(code = 404, message = "Unable to find business levels.", response = ServiceError.class),
                    @ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
    @PostMapping(value = PCPSearchServiceConstants.GET_BUSINESS_LEVELS_URI, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BlResolutionResponse> getBusinessLevels(@RequestBody BlServiceRequest blServiceRequest)  {
		log.info("START PCPSearchServiceController.getBusinessLevels");
		BlResolutionResponse blResolutionResponse = pcpSearchService.getBusinessLevels(blServiceRequest);
		ResponseEntity<BlResolutionResponse> responseEntity = new ResponseEntity<>(blResolutionResponse, HttpStatus.OK); 
		log.info("END PCPSearchServiceController.getBusinessLevels");
		return responseEntity;
	}

	@ApiOperation(
			value = PCPSearchServiceConstants.SUMMARY_BP_AND_BUSINESS_LEVELS, 
			notes = PCPSearchServiceConstants.SUMMARY_BP_AND_BUSINESS_LEVELS_NOTES, 
			response = BlResolutionResponse.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully found BP and Business levels.", response = BlResolutionResponse.class),
                    @ApiResponse(code = 400, message = "Bad request.", response = ServiceError.class),
//                    @ApiResponse(code = 403, message = "Unauthorized", response = ServiceError.class),
                    @ApiResponse(code = 404, message = "Unable to find BP and Business levels.", response = ServiceError.class),
                    @ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
    @PostMapping(value = PCPSearchServiceConstants.GET_BP_AND_BUSINESS_LEVELS_URI, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BPBLResolutionResponse> getBPsAndBussinessLevels(@RequestBody BlServiceRequest blServiceRequest) {
		log.info("START PCPSearchServiceController.getBPsAndBussinessLevels");
		BPBLResolutionResponse bpblResolutionResponse = pcpSearchService.getBPsAndBussinessLevels(blServiceRequest);
		ResponseEntity<BPBLResolutionResponse> responseEntity = new ResponseEntity<>(bpblResolutionResponse, HttpStatus.OK); 
		log.info("END PCPSearchServiceController.getBPsAndBussinessLevels");
		return responseEntity;
	}

	@ApiOperation(
			value = PCPSearchServiceConstants.SUMMARY_STATE_PCP_ASSIGNMENT, 
			notes = PCPSearchServiceConstants.SUMMARY_STATE_PCP_ASSIGNMENT_NOTES, 
			response = StatePcpAssignmentResponse.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully found state pcp assignment data.", response = StatePcpAssignmentResponse.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ServiceError.class),
//                    @ApiResponse(code = 403, message = "Unauthorized", response = ServiceError.class),
                    @ApiResponse(code = 404, message = "Unable to find state pcp assignment data.", response = ServiceError.class),
                    @ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
    @PostMapping(value = PCPSearchServiceConstants.GET_STATE_PCP_ASSIGNMENT_URI, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<StatePcpAssignmentResponse> getStatePCPAssignment(@RequestBody StatePcpAssignmentRequest statePcpAssignmentRequest) {
		log.info("START PCPSearchServiceController.getStatePCPAssignment");
		StatePcpAssignmentResponse statePcpAssignmentResponse = pcpSearchService.getStatePCPAssignment(statePcpAssignmentRequest);
		ResponseEntity<StatePcpAssignmentResponse> responseEntity = new ResponseEntity<>(statePcpAssignmentResponse, HttpStatus.OK); 
		log.info("END PCPSearchServiceController.getStatePCPAssignment");
		return responseEntity;
	}

	@ApiOperation(
			value = PCPSearchServiceConstants.SUMMARY_GROUP_BENEFIT_PACK_BUSINESS_LEVEL, 
			notes = PCPSearchServiceConstants.SUMMARY_GROUP_BENEFIT_PACK_BUSINESS_LEVEL_NOTES, 
			response = BPBLResolutionResponse.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully found group benefit package business levels.", response = BPBLResolutionResponse.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ServiceError.class),
//                    @ApiResponse(code = 403, message = "Unauthorized", response = ServiceError.class),
                    @ApiResponse(code = 404, message = "Unable to find group benefit package business levels.", response = ServiceError.class),
                    @ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
    @PostMapping(value = PCPSearchServiceConstants.GET_GROUP_BENEFIT_PACK_BUSINESS_LEVEL_URI, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BPBLResolutionResponse> groupBenefitPackBussinessLevel(@RequestBody BlServiceRequest blServiceRequest) {
		log.info("START PCPSearchServiceController.groupBenefitPackBussinessLevel");
		BPBLResolutionResponse bpblResolutionResponse = pcpSearchService.groupBenefitPackBussinessLevel(blServiceRequest);
		ResponseEntity<BPBLResolutionResponse> responseEntity = new ResponseEntity<>(bpblResolutionResponse, HttpStatus.OK); 
		log.info("END PCPSearchServiceController.groupBenefitPackBussinessLevel");
		return responseEntity;
	}

	@ApiOperation(
			value = PCPSearchServiceConstants.SUMMARY_PROVIDER_VALIDATION, 
			notes = PCPSearchServiceConstants.SUMMARY_PROVIDER_VALIDATION_NOTES, 
			response = PCPAssignmentResponse.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully processed provider validation", response = PCPAssignmentResponse.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ServiceError.class),
//                    @ApiResponse(code = 403, message = "Unauthorized", response = ServiceError.class),
                    @ApiResponse(code = 404, message = "Unable to find provider for validation.", response = ServiceError.class),
                    @ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
    @PostMapping(value = PCPSearchServiceConstants.GET_PROVIDER_VALIDATION_URI, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PCPAssignmentResponse> providerValidate(@RequestBody PcpAssignmentRequest pcpAssignmentRequest) {
		log.info("START PCPSearchServiceController.providerValidate");
		PCPAssignmentResponse pcpAssignmentResponse = pcpSearchService.providerValidate(pcpAssignmentRequest);
		ResponseEntity<PCPAssignmentResponse> responseEntity = new ResponseEntity<>(pcpAssignmentResponse, HttpStatus.OK); 
		log.info("END PCPSearchServiceController.providerValidate");
		return responseEntity;
	}

	@ApiOperation(
			value = PCPSearchServiceConstants.SUMMARY_RETRIEVE_DISTINCT_EXCEPTION_GROUPS, 
			notes = PCPSearchServiceConstants.SUMMARY_RETRIEVE_DISTINCT_EXCEPTION_GROUPS_NOTES, 
			response = RetrieveDistinctExceptionGroupsRes.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully Retrived Distinct Exception Groups", response = RetrieveDistinctExceptionGroupsRes.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ServiceError.class),
//                    @ApiResponse(code = 415, message = "Unsupported Media Type", response = ServiceError.class),
                    @ApiResponse(code = 404, message = "Unable to Retrive Distinct ServiceError Groups.", response = ServiceError.class),
                    @ApiResponse(code = 500, message = "Internal server error.", response = ServiceError.class) })
	@ResponseBody
	@MethodExecutionTime
    @GetMapping(value = PCPSearchServiceConstants.GET_RETRIEVE_DISTINCT_EXCEPTION_GROUPS_URI, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RetrieveDistinctExceptionGroupsRes> retrieveDistinctExceptionGroups( @ApiParam(value = "retrieve-distinct") @RequestParam(name = "retrieve-distinct", required = true) String retrieveDistinct) {
		log.info("START PCPSearchServiceController.retrieveDistinctExceptionGroups");
		RetrieveDistinctExceptionGroupsRes distinctExceptionGroupsRes = pcpSearchService.retrieveDistinctExceptionGroups(retrieveDistinct);
		ResponseEntity<RetrieveDistinctExceptionGroupsRes> responseEntity = new ResponseEntity<>(distinctExceptionGroupsRes, HttpStatus.OK); 
		log.info("END PCPSearchServiceController.retrieveDistinctExceptionGroups");
		return responseEntity;
	}

}
