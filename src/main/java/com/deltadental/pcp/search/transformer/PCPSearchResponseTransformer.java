package com.deltadental.pcp.search.transformer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.BenefitPackage;
import com.deltadental.pcp.search.domain.BusinessLevels;
import com.deltadental.pcp.search.domain.EnrolleeDetail;
import com.deltadental.pcp.search.domain.Facility;
import com.deltadental.pcp.search.domain.OfficeHour;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PCPResponse;
import com.deltadental.pcp.search.domain.PcpProvider;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.pd.entities.FacilitySearchEntity;
import com.deltadental.platform.pcp.stub.PcpAssignmentResponse;
import com.deltadental.platform.pcp.stub.PcpResponse;
import com.deltadental.platform.pcp.stub.PcpValidateResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PCPSearchResponseTransformer {

	private static final String FAIL = "Fail";

	/**
	 * This method transforms stub pcp validate response to rest pcp validate response
	 * @param pcpValidateResponse
	 * @return PCPAssignmentResponse
	 */
	public PCPAssignmentResponse transformPcpValidateResponse(PcpValidateResponse pcpValidateResponse) {
		PcpAssignmentResponse clientPcpAssignmentResponse = pcpValidateResponse.getReturn();
		PCPAssignmentResponse pcpAssignmentResponse = getPCPAssignmentResponse(clientPcpAssignmentResponse);
		return pcpAssignmentResponse;
	}

	/**
	 * This method maps stub provider response to json provider response
	 * @param providersResponse
	 * @return ProvidersResponse
	 */
	public ProvidersResponse transformProvidersResponse(com.deltadental.platform.pcp.stub.ProvidersResponse providersResponse) {
		log.info("START PCPSearchResponseTransformer.transformProvidersResponse");
		ProvidersResponse response = new ProvidersResponse();
		PcpAssignmentResponse _return = providersResponse.getReturn();
		PCPAssignmentResponse pcpAssignmentResponse = getPCPAssignmentResponse(_return);
		response.setPcpAssignmentResponse(pcpAssignmentResponse);
		updateProviderResponseErrorCode(response);
		log.info("END PCPSearchResponseTransformer.transformProvidersResponse");
		return response;
	}
	
	/**
	 * This method transforms Facility search entity to rest facility
	 * @param facilitySearchEntity
	 * @return Facility
	 */
	public Facility mapFacility(FacilitySearchEntity facilitySearchEntity) {
		log.info("START : PCPSearchService.map");
		Facility facility = null;
		if (null != facilitySearchEntity) {
			facility = Facility.builder()
					.facilityId(facilitySearchEntity.getFacilityId())
					.facilityName(facilitySearchEntity.getGroupPracticeName())
					.facilityStatus(facilitySearchEntity.getFacilityStatus())
					.address(facilitySearchEntity.getAddress())
					.phoneNumber(facilitySearchEntity.getPhoneNumber())
					.specility(facilitySearchEntity.getProviderSpecialty())
					.contracted(facilitySearchEntity.getContracted())
					.effectiveDate(StringUtils.equalsIgnoreCase(facilitySearchEntity.getEffectiveDate(), "NA") ? "" : facilitySearchEntity.getEffectiveDate())
					.enrollStatus(facilitySearchEntity.getEnrollStatus())
					.providerSpecialtyDec(facilitySearchEntity.getProviderSpecialtyDec())
					.providerLanguages(Arrays.asList(facilitySearchEntity.getProviderLanguages().split(","))).build();
			log.info("Facility mapped {} ", facility);
		}
		log.info("END : PCPSearchService.map");
		return facility;
	}
	
	/**
	 * This method update the response status.
	 * @param response
	 */
	private void updateProviderResponseErrorCode(com.deltadental.pcp.search.domain.ProvidersResponse response) {
		log.info("START PCPAssignmentService.setProcessStatusCode()");
		if(response != null) {
			PCPAssignmentResponse pcpAssignmentResponse = response.getPcpAssignmentResponse();
			if(null != pcpAssignmentResponse) {
				List<PCPResponse> pcpResponses = pcpAssignmentResponse.getPcpResponses();
				if(CollectionUtils.isNotEmpty(pcpResponses)) {
					for (PCPResponse pcpResponse : pcpResponses) {
						List<EnrolleeDetail> enrollees = pcpResponse.getEnrollees();
						for (EnrolleeDetail enrolleeDetail : enrollees) {
							List<String> errorMessages = enrolleeDetail.getErrorMessages();
							if(CollectionUtils.isNotEmpty(errorMessages)) {
								pcpAssignmentResponse.setProcessStatusCode(FAIL);
								return;
							}
						}
					}
				}
			}
		}
		log.info("END PCPAssignmentService.setProcessStatusCode()");
	}
	
	/**
	 * This method transforms stub pcp assignment response to rest pcp assignment response
	 * @param clientPcpAssignmentResponse
	 * @return PCPAssignmentResponse
	 */
	private PCPAssignmentResponse getPCPAssignmentResponse(PcpAssignmentResponse clientPcpAssignmentResponse) {
		log.info("START PCPSearchResponseTransformer.getPCPAssignmentResponse");
		if(clientPcpAssignmentResponse != null) {
			List<PcpResponse> clientPcpResponses = clientPcpAssignmentResponse.getPcpResponses();
			List<PCPResponse> pcpResponses = clientPcpResponses.stream().map(this::transformPCPResponse).collect(Collectors.toList());
			PCPAssignmentResponse pcpAssignmentResponse = PCPAssignmentResponse.builder()
					.processStatusCode(clientPcpAssignmentResponse.getProcessStatusCode())
					.processStatusDescription(clientPcpAssignmentResponse.getProcessStatusDescription())
					.sourceSystem(clientPcpAssignmentResponse.getSourceSystem())
					.pcpResponses(pcpResponses)
					.build();
			log.info("END PCPSearchResponseTransformer.getPCPAssignmentResponse");
			return pcpAssignmentResponse;
		}
		log.info("END PCPSearchResponseTransformer.getPCPAssignmentResponse");
		return null;
	}

	/**
	 * This method transforms stub pcp response to rest pcp response
	 * @param clientPcpRespnse
	 * @return PCPResponse
	 */
	private PCPResponse transformPCPResponse(PcpResponse clientPcpRespnse) {
		log.info("START PCPSearchResponseTransformer.transformPCPResponse");
		if(clientPcpRespnse != null) {
			PCPResponse pcpResponse = PCPResponse.builder().contractId(clientPcpRespnse.getContractID())
					.enrollees(clientPcpRespnse.getEnrollees().stream().map(this::transformEnrolleeDetail).collect(Collectors.toList()))
					.build();
			log.info("END PCPSearchResponseTransformer.transformPCPResponse");
			return pcpResponse;
		}
		log.info("END PCPSearchResponseTransformer.transformPCPResponse");
		return null;
	}

	/**
	 * This method transforms stub enrollee details to rest enrollee detail
	 * @param clientEnrolleeDetail
	 * @return EnrolleeDetail
	 */
	private EnrolleeDetail transformEnrolleeDetail(com.deltadental.platform.pcp.stub.EnrolleeDetail clientEnrolleeDetail) {
		log.info("START PCPSearchResponseTransformer.transformEnrolleeDetail");
		if(clientEnrolleeDetail != null) {
			EnrolleeDetail enrolleeDetail = EnrolleeDetail.builder()
					.benefitPackages(transformWSBenefitPackages(clientEnrolleeDetail.getBenefitpackages()))
					.businessLevels(clientEnrolleeDetail.getBusinesslevels().stream().map(this::transformBusinessLevels).collect(Collectors.toList()))
					.divisionNumber(StringUtils.trimToNull(clientEnrolleeDetail.getDivisionNumber()))
					.enrolleeStatusCode(clientEnrolleeDetail.getEnrolleeStatusCode())
					.errorMessages(clientEnrolleeDetail.getErrorMessages())
					.groupNumber(StringUtils.trimToNull(clientEnrolleeDetail.getGroupNumber()))
					.memberType(clientEnrolleeDetail.getMemberType())
					.numberOfFacilities(clientEnrolleeDetail.getNumberOfFacilities())
					.pcpProviders(clientEnrolleeDetail.getPcpproviders().stream().map(this::transformPcpProvider).collect(Collectors.toList()))
					.pcpValidationFlag(clientEnrolleeDetail.isPcpValidationFlag())
					.recordIdentifier(clientEnrolleeDetail.getRecordIdentifier())
					.build();
			log.info("END PCPSearchResponseTransformer.transformEnrolleeDetail");
			return enrolleeDetail;
		}
		log.info("END PCPSearchResponseTransformer.transformEnrolleeDetail");
		return null;
	}

	/**
	 * This method transforms stub pcp provider to rest pcp provider
	 * @param clientPcpProvider
	 * @return PcpProvider
	 */
	private PcpProvider transformPcpProvider(com.deltadental.platform.pcp.stub.PcpProvider clientPcpProvider) {
		log.info("START PCPSearchResponseTransformer.transformPcpProvider");
		if(clientPcpProvider != null) {
			PcpProvider pcpprovider = PcpProvider.builder()
					.businessLevels(clientPcpProvider.getBusinesslevels().stream().map(this::transformBusinessLevels).collect(Collectors.toList()))
					.distance(clientPcpProvider.getDistance()).facilityLatitude(clientPcpProvider.getFacilityLatitude())
					.facilityLongitude(clientPcpProvider.getFacilityLongitude())
					.groupPracticeNumber(clientPcpProvider.getGroupPracticeNumber())
					.languages(clientPcpProvider.getLanguages()).maxAge(clientPcpProvider.getMaxAge())
					.maxMemberCount(clientPcpProvider.getMaxMemberCount()).minAge(clientPcpProvider.getMinAge())
					.mtvNetworkId(clientPcpProvider.getMtvNetworkId())
					.officeHours(transformOfficeHours(clientPcpProvider.getOfficeHours()))
					.patientCount(clientPcpProvider.getPatientCount())
					.practiceLocationNumber(clientPcpProvider.getPracticeLocationNumber())
					.practiceLocationPhone(clientPcpProvider.getPracticeLocationPhone())
					.providerAccessibleFacility(clientPcpProvider.getProviderAccessibleFacility())
					.providerAddress(transformProviderAddress(clientPcpProvider.getProviderAddress()))
					.providerFacilityId(clientPcpProvider.getProviderFacilityId())
					.providerFacilityName(clientPcpProvider.getProviderFacilityName())
					.providerType(clientPcpProvider.getProviderType())
					.providerWorkMode(clientPcpProvider.getProviderWorkMode())
					.specialtyCode(clientPcpProvider.getSpecialtyCode())
					.specialtyDescription(clientPcpProvider.getSpecialtyDescription())
					.build();
			log.info("END PCPSearchResponseTransformer.transformPcpProvider");
			return pcpprovider;
		}
		log.info("END PCPSearchResponseTransformer.transformPcpProvider");
		return null;
	}

	/**
	 * This method transform stub provider address to rest provider address
	 * @param providerAddress
	 * @return Address
	 */
	private Address transformProviderAddress(com.deltadental.platform.pcp.stub.Address providerAddress) {
		log.info("START PCPSearchResponseTransformer.transformProviderAddress");
		if(providerAddress != null) {
			Address address = Address.builder()
					.addressLine1(providerAddress.getAddressLine1())
					.addressLine2(providerAddress.getAddressLine2())
					.city(providerAddress.getCity())
					.state(providerAddress.getState())
					.zipCode(providerAddress.getZipCode())
					.build();
			log.info("END PCPSearchResponseTransformer.transformProviderAddress");
			return address;
			}
		log.info("END PCPSearchResponseTransformer.transformProviderAddress");
		return null;
	}

	/**
	 * This method transforms stub office hours to rest office hours
	 * @param officeHours
	 * @return OfficeHour
	 */
	private OfficeHour transformOfficeHours(com.deltadental.platform.pcp.stub.OfficeHour officeHours) {
		log.info("START PCPSearchResponseTransformer.transformOfficeHours");
		if(officeHours != null) {
			OfficeHour oh = OfficeHour.builder().officeHours(officeHours.getOfficeHours()).build();
			log.info("END PCPSearchResponseTransformer.transformOfficeHours");
			return oh;
		}
		log.info("END PCPSearchResponseTransformer.transformOfficeHours");
		return null;
	}

	/**
	 * This method transforms stub business levels to rest business levels
	 * @param wsBl
	 * @return BusinessLevels
	 */
	private BusinessLevels transformBusinessLevels(com.deltadental.platform.pcp.stub.BusinessLevels wsBl) {
		log.info("START PCPSearchResponseTransformer.transformBusinessLevels");
		if(wsBl != null) {
			BusinessLevels domainBl = BusinessLevels.builder()
					.businessLevel4(StringUtils.trimToNull(wsBl.getBusinessLevel4()))
					.businessLevel5(StringUtils.trimToNull(wsBl.getBusinessLevel5()))
					.businessLevel6(StringUtils.trimToNull(wsBl.getBusinessLevel6()))
					.businessLevel7(StringUtils.trimToNull(wsBl.getBusinessLevel7()))
					.effectiveDate(StringUtils.trimToNull(wsBl.getEffectiveDate()))
					.endDate(StringUtils.trimToNull(wsBl.getEndDate()))
					.networkId(StringUtils.trimToNull(wsBl.getNetworkId()))
					.classCode(StringUtils.trimToNull(wsBl.getClassCode()))
					.build();
			log.info("END PCPSearchResponseTransformer.transformBusinessLevels");
			return domainBl;
		}
		log.info("END PCPSearchResponseTransformer.transformBusinessLevels");
		return null;
	}

	/** 
	 * This method transform stub benefit package to rest benefit package
	 * @param stubBp
	 * @return BenefitPackage
	 */
	private BenefitPackage transformBenefitPackage(com.deltadental.platform.pcp.stub.BenefitPackage stubBp) {
		log.info("START PCPSearchResponseTransformer.transformBenefitPackage");
		if(stubBp != null) {
			BenefitPackage bp = BenefitPackage.builder()
					.benefitPackageId(StringUtils.trimToNull(stubBp.getBenefitPackageId()))
					.bpIdEffectiveDate(StringUtils.trimToNull(stubBp.getBpideffectiveDate()))
					.bpIdEndDate(StringUtils.trimToNull(stubBp.getBpidendDate()))
					.build();
			log.info("END PCPSearchResponseTransformer.transformBenefitPackage");
			return bp;
		}
		log.info("END PCPSearchResponseTransformer.transformBenefitPackage");
		return null;
	}

	/**
	 * This method transforms stub benefit packages to rest benefit packages
	 * @param benefitpackages
	 * @return List<BenefitPackage>
	 */
	private List<BenefitPackage> transformWSBenefitPackages(List<com.deltadental.platform.pcp.stub.BenefitPackage> benefitpackages) {
		log.info("START PCPSearchResponseTransformer.transformWSBenefitPackages");
		List<BenefitPackage> domainsBps = benefitpackages.stream().map(this::transformBenefitPackage).collect(Collectors.toList());
		log.info("END PCPSearchResponseTransformer.transformWSBenefitPackages");
		return domainsBps;
	}

	

}
