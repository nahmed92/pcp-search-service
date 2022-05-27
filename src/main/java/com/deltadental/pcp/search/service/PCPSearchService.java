package com.deltadental.pcp.search.service;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltadental.pcp.search.domain.Facility;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.error.PCPSearchServiceErrors;
import com.deltadental.pcp.search.pd.entities.FacilitySearchEntity;
import com.deltadental.pcp.search.pd.repos.FacilitySearchRepo;
import com.deltadental.pcp.search.service.impl.ProvidersAuditService;
import com.deltadental.pcp.search.transformer.PCPSearchRequestTransformer;
import com.deltadental.pcp.search.transformer.PCPSearchResponseTransformer;
import com.deltadental.pcp.soap.client.PCPAssignmentSoapClient;
import com.deltadental.platform.common.annotation.aop.MethodExecutionTime;
import com.deltadental.platform.common.exception.ServiceException;
import com.deltadental.platform.pcp.stub.GetProviders;
import com.deltadental.platform.pcp.stub.GetProvidersResponse;
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.PcpValidateResponse;
import com.deltadental.platform.pcp.stub.Providers;
import com.deltadental.platform.pcp.stub.ProvidersResponse;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service("pcpSearchService")
@Slf4j
@Data
public class PCPSearchService {

	@Autowired
	private PCPAssignmentSoapClient pcpAssignmentSoapClient;

	@Autowired
	private PCPSearchRequestTransformer pcpSearchRequestTransformer;

	@Autowired
	private PCPSearchResponseTransformer pcpSearchResponseTransformer;

	@Autowired
	private ProvidersAuditService providersAuditService;
	
//	@Autowired
//	private FacilitySearchRepo facilitySearchRepo;

	@Autowired
	private FacilitySearchRepo facilitySearchRepo;
	
	@MethodExecutionTime
	public PCPAssignmentResponse getProviders(@Valid PcpAssignmentRequest pcpAssignmentRequest)
			throws ServiceException {
		log.info("START PCPSearchService.getProviders");
		PCPAssignmentResponse pcpAssignmentResponse = null;
		try {
			GetProviders getProviders = pcpSearchRequestTransformer.transformGetProviders(pcpAssignmentRequest);
			GetProvidersResponse getProvidersResponse = pcpAssignmentSoapClient.getProviders(getProviders);
			pcpAssignmentResponse = pcpSearchResponseTransformer.transformGetProvidersResponse(getProvidersResponse);
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + pcpAssignmentRequest, exception);
			throw PCPSearchServiceErrors.GETPROVIDERS_SERVER_ERROR.createException(pcpAssignmentRequest);
		}
		log.info("END PCPSearchService.getProviders");
		return pcpAssignmentResponse;
	}	

	@MethodExecutionTime
	public com.deltadental.pcp.search.domain.ProvidersResponse searchProviders(ProvidersRequest providersRequest)
			throws ServiceException {
		log.info("START PCPSearchService.providers");
		com.deltadental.pcp.search.domain.ProvidersResponse response = null;
		try {
			Providers providers = pcpSearchRequestTransformer.transformProvidersRequest(providersRequest);
			ProvidersResponse providersResponse = pcpAssignmentSoapClient.providers(providers);
			response = pcpSearchResponseTransformer.transformProvidersResponse(providersResponse);
		} catch (Exception exception) {
			log.error("Unable to fetch providers for request" + providersRequest, exception);
			throw PCPSearchServiceErrors.PROVIDERS_SEARCH_ERROR.createException(providersRequest);
		} finally {
			providersAuditService.save(providersRequest, response);
		}
		log.info("END PCPSearchService.providers");
		return response;
	}

	@MethodExecutionTime
	public PCPAssignmentResponse validate(PCPValidateRequest pcpValidateRequest) throws ServiceException {
		log.info("START PCPSearchService.validate");
		PCPAssignmentResponse response = null;
		try {
			PcpValidate providers = pcpSearchRequestTransformer.transformPcpValidateRequest(pcpValidateRequest);
			PcpValidateResponse pcpValidateResponse = pcpAssignmentSoapClient.pcpValidate(providers);
			response = pcpSearchResponseTransformer.transformPcpValidateResponse(pcpValidateResponse);
		} catch (Exception exception) {
			log.error("Unable to perform pcp validation for request {} ", pcpValidateRequest, exception);
			throw PCPSearchServiceErrors.PROVIDER_VALIDATE_ERROR.createException(pcpValidateRequest);
		}
		log.info("END PCPSearchService.validate");
		return response;
	}

	@MethodExecutionTime
	public Facility searchFacility(String facilityId) throws ServiceException {
		log.info("START PCPSearchServiceImpl.searchFacility");
		if(StringUtils.isBlank(facilityId)) {
			log.error("Facility id is mandatory.");
			throw PCPSearchServiceErrors.FACILITY_ID_REQUIRED.createException();
		}		
		FacilitySearchEntity facilitySearchEntity = null;
		try {
			log.info("Searching for facility id {} ",facilityId);
			facilitySearchEntity = facilitySearchRepo.searchFacilityByFacilityId(facilityId);
			log.info("Found Facility {} for facility id {} ",facilitySearchEntity, facilityId);
		} catch (Exception exception) {
			log.error("Unable to search facility for facility id {} ", facilityId, exception);
			throw PCPSearchServiceErrors.FACILITY_SEARCH_ERROR.createException();
		}
		Facility facility = map(facilitySearchEntity);
		if(facility == null) {
			log.info("Facility not found for facility id {} ",facilityId);
			throw PCPSearchServiceErrors.FACILITY_NOT_FOUND.createException();
		} 
		log.info("END PCPSearchServiceImpl.searchFacility");
		return facility;
	}

	private Facility map(FacilitySearchEntity facilitySearchEntity) {
		log.info("START : PCPSearchService.map");
		Facility facility = null;
		if(null != facilitySearchEntity) {
			facility = Facility.builder()					
			.facilityId(facilitySearchEntity.getFacilityId())
			.facilityName(facilitySearchEntity.getGroupPracticeName())			
			.facilityStatus(facilitySearchEntity.getFacilityStatus())
			.addressLine1(facilitySearchEntity.getAddressLine1())
			.addressLine2(facilitySearchEntity.getAddressLine2())
			.addressLine3(facilitySearchEntity.getAddressLine3())
			.city(facilitySearchEntity.getCity())
			.state(facilitySearchEntity.getState())
			.zip(facilitySearchEntity.getZip())
			.phoneNumber(facilitySearchEntity.getPhoneNumber())
			.specility(facilitySearchEntity.getProviderSpecialty())
			.contracted(facilitySearchEntity.getContracted())
			.effectiveDate(facilitySearchEntity.getEffectiveDate())
			.enrollStatus(facilitySearchEntity.getEnrollStatus())
			.providerSpecialtyDec(facilitySearchEntity.getProviderSpecialtyDec())
			.build();
			log.info("Facility mapped {} ", facility);
		}
		log.info("END : PCPSearchService.map");
		return facility;
	}
}
