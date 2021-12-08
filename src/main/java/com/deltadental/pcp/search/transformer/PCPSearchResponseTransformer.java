package com.deltadental.pcp.search.transformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.BPBLResolutionResponse;
import com.deltadental.pcp.search.domain.BPBusinessLevels;
import com.deltadental.pcp.search.domain.BenefitPackage;
import com.deltadental.pcp.search.domain.BlResolutionResponse;
import com.deltadental.pcp.search.domain.BusinessLevels;
import com.deltadental.pcp.search.domain.EnrolleeBLRespose;
import com.deltadental.pcp.search.domain.EnrolleeBPBLResponse;
import com.deltadental.pcp.search.domain.EnrolleeDetail;
import com.deltadental.pcp.search.domain.Facility;
import com.deltadental.pcp.search.domain.FacilityResponse;
import com.deltadental.pcp.search.domain.OfficeHour;
import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PCPResponse;
import com.deltadental.pcp.search.domain.PcpProvider;
import com.deltadental.pcp.search.domain.StatePcpAssignmentResponse;
import com.deltadental.platform.pcp.stub.BpBusinessLevels;
import com.deltadental.platform.pcp.stub.BpblResolutionResponse;
import com.deltadental.platform.pcp.stub.FacilitySearchResponse;
import com.deltadental.platform.pcp.stub.GetBPsAndBussinessLevelsResponse;
import com.deltadental.platform.pcp.stub.GetBussinessLevelsResponse;
import com.deltadental.platform.pcp.stub.GetProvidersResponse;
import com.deltadental.platform.pcp.stub.GetStatePCPAssignmentResponse;
import com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevelResponse;
import com.deltadental.platform.pcp.stub.PcpAssignmentResponse;
import com.deltadental.platform.pcp.stub.PcpResponse;
import com.deltadental.platform.pcp.stub.ProviderValidateResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PCPSearchResponseTransformer {

	public PCPAssignmentResponse transformGetProvidersResponse(GetProvidersResponse getProvidersResponse) {
		log.info("START PCPSearchResponseTransformer.transformGetProvidersResponse");
		PcpAssignmentResponse clientPcpAssignmentResponse = getProvidersResponse.getReturn();
		PCPAssignmentResponse pcpAssignmentResponse = getPCPAssignmentResponse(clientPcpAssignmentResponse);
		log.info("END PCPSearchResponseTransformer.transformGetProvidersResponse");
		return pcpAssignmentResponse;
	}

	public FacilityResponse transformFaclilitySearchResponse(FacilitySearchResponse facilitySearchResponse) {
		log.info("START PCPSearchResponseTransformer.transformFaclilitySearchResponse");
		com.deltadental.platform.pcp.stub.FacilityResponse wsFacilityResponse = facilitySearchResponse.getReturn();
		List<com.deltadental.platform.pcp.stub.Facility> wsFacilities = wsFacilityResponse.getFacility();
		List<Facility> domainFacilities = wsFacilities.stream().map(this::transformFacility).collect(Collectors.toList());
		FacilityResponse facilityResponse = FacilityResponse.builder().facility(domainFacilities).build();
		log.info("END PCPSearchResponseTransformer.transformFaclilitySearchResponse");
		return facilityResponse;
	}

	private Facility transformFacility(com.deltadental.platform.pcp.stub.Facility wsFacility) {
		log.info("START PCPSearchResponseTransformer.transformFacility");
		Facility domainFacility = Facility.builder()
				.addressLine1(wsFacility.getAddressLine1())
				.addressLine2(wsFacility.getAddressLine2())
				.addressLine3(wsFacility.getAddressLine3())
				.city(wsFacility.getCity())
				.contracted(wsFacility.getContracted())
				.effectiveDate(wsFacility.getEffectiveDate())
				.enrollStatus(wsFacility.getEffectiveDate())
				.facilityID(wsFacility.getFacilityID())
				.facilityName(wsFacility.getFacilityName())
				.facilitystatus(wsFacility.getFacilitystatus())
				.phoneNumber(wsFacility.getPhoneNumber())
				.specility(wsFacility.getSpecility())
				.state(wsFacility.getState())
				.zip(wsFacility.getZip())
				.build();
		log.info("END PCPSearchResponseTransformer.transformFacility");
		return domainFacility;
	}

	public StatePcpAssignmentResponse transformGetStatePCPAssignmentResponse(GetStatePCPAssignmentResponse getStatePCPAssignmentResponse) {
		log.info("START PCPSearchResponseTransformer.transformGetStatePCPAssignmentResponse");
		com.deltadental.platform.pcp.stub.StatePcpAssignmentResponse wsStatePcpAssignmentResponse = getStatePCPAssignmentResponse.getReturn();
		StatePcpAssignmentResponse statePcpAssignmentResponse = StatePcpAssignmentResponse.builder()
				.divisionNumber(wsStatePcpAssignmentResponse.getDivisionNumber())
				.isGroupDivEligibleforAutoPcp(wsStatePcpAssignmentResponse.isGroupDivEligibleforAutoPcp())
				.groupNumber(wsStatePcpAssignmentResponse.getGroupNumber())
				.returnCode(wsStatePcpAssignmentResponse.getReturnCode())
				.returnDescription(wsStatePcpAssignmentResponse.getReturnDescription())
				.state(wsStatePcpAssignmentResponse.getState())
				.isStateEligibleforPcp(wsStatePcpAssignmentResponse.isStateEligibleforPcp())
				.unlimitedSplitFamily_INDV_Flag(wsStatePcpAssignmentResponse.isUnlimitedSplitFamilyINDVFlag())
				.unlimitedSplitFamilyFlag(wsStatePcpAssignmentResponse.isUnlimitedSplitFamilyFlag()).build();
		log.info("END PCPSearchResponseTransformer.transformGetStatePCPAssignmentResponse");
		return statePcpAssignmentResponse;
	}

	public BlResolutionResponse transformStubGetBussinessLevelsResponse(GetBussinessLevelsResponse getBussinessLevelsResponse) {
		log.info("START PCPSearchResponseTransformer.transformStubGetBussinessLevelsResponse");
		com.deltadental.platform.pcp.stub.BlResolutionResponse wsBlResolutionResponse = getBussinessLevelsResponse.getReturn();
		List<com.deltadental.platform.pcp.stub.EnrolleeBLRespose> wsEnrolleeBLResposes = wsBlResolutionResponse.getEnrollee();
		List<EnrolleeBLRespose> domainEnrolleeList = wsEnrolleeBLResposes.stream().map(this::transformEnrolleeBLRespose).collect(Collectors.toList());
		BlResolutionResponse blResolutionResponse = BlResolutionResponse.builder().enrollee(domainEnrolleeList).build();
		log.info("END PCPSearchResponseTransformer.transformStubGetBussinessLevelsResponse");
		return blResolutionResponse;
	}

	private EnrolleeBLRespose transformEnrolleeBLRespose(com.deltadental.platform.pcp.stub.EnrolleeBLRespose enrolleeBLRespose) {
		log.info("START PCPSearchResponseTransformer.transformEnrolleeBLRespose");
		EnrolleeBLRespose domainEnrollee = EnrolleeBLRespose.builder()
				.BusinessLevelCount(enrolleeBLRespose.getBusinessLevelCount())
				.benPkgList(transformWSBenefitPackages(enrolleeBLRespose.getBenPkgList()))
				.businessLevels(enrolleeBLRespose.getBusinessLevels().stream().map(this::transformBusinessLevels).collect(Collectors.toList()))
				.divisionNumber(enrolleeBLRespose.getDivisionNumber())
				.errorMessage(enrolleeBLRespose.getErrorMessage())
				.groupNumber(enrolleeBLRespose.getGroupNumber())
				.memberType(enrolleeBLRespose.getMemberType())
				.providerId(enrolleeBLRespose.getProviderId())
				.statusCode(enrolleeBLRespose.getStatusCode())
				.build();
		log.info("END PCPSearchResponseTransformer.transformEnrolleeBLRespose");
		return domainEnrollee;
	}

	public BPBLResolutionResponse transformStubGroupBenefitPackBussinessLevelResponse(GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevelResponse) {
		log.info("START PCPSearchResponseTransformer.transformStubGroupBenefitPackBussinessLevelResponse");
		BpblResolutionResponse wsBpblResolutionResponse = groupBenefitPackBussinessLevelResponse.getReturn();
		List<com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse> wsEnrolleeBPBLResponses = wsBpblResolutionResponse.getEnrollee();
		List<EnrolleeBPBLResponse> domainEnrolleeBPBLResponses = wsEnrolleeBPBLResponses.stream().map(this::transformEnrolleeBPBLResponse).collect(Collectors.toList());
		BPBLResolutionResponse bpblResolutionResponse = BPBLResolutionResponse.builder().enrollee(domainEnrolleeBPBLResponses).build();
		log.info("END PCPSearchResponseTransformer.transformStubGroupBenefitPackBussinessLevelResponse");
		return bpblResolutionResponse;
	}

	public PCPAssignmentResponse transformStubProviderValidateResponse(ProviderValidateResponse providerValidateResponse) {
		log.info("START PCPSearchResponseTransformer.transformStubProviderValidateResponse");
		PcpAssignmentResponse clientPcpAssignmentResponse = providerValidateResponse.getReturn();
		PCPAssignmentResponse pcpAssignmentResponse = getPCPAssignmentResponse(clientPcpAssignmentResponse);
		log.info("END PCPSearchResponseTransformer.transformStubProviderValidateResponse");
		return pcpAssignmentResponse;
	}

	public BlResolutionResponse transformGetBPsAndBussinessLevelsResponse(GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevelsResponse) {
		log.info("START PCPSearchResponseTransformer.transformGetBPsAndBussinessLevelsResponse");
		BpblResolutionResponse wsBpblResolutionResponse = getBPsAndBussinessLevelsResponse.getReturn();
		List<com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse> wsEnrolleeBPBLResponseList = wsBpblResolutionResponse.getEnrollee();
		List<EnrolleeBLRespose> domainEnrolleeBLResponses = wsEnrolleeBPBLResponseList.stream().map(this::transformEnrolleeBLRespose).collect(Collectors.toList());
		BlResolutionResponse blResolutionResponse = BlResolutionResponse.builder().enrollee(domainEnrolleeBLResponses).build();
		log.info("END PCPSearchResponseTransformer.transformGetBPsAndBussinessLevelsResponse");
		return blResolutionResponse;
	}

	private EnrolleeBLRespose transformEnrolleeBLRespose(com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse wsEnrolleeBPBLResponse) {
		log.info("START PCPSearchResponseTransformer.transformEnrolleeBLRespose");
		EnrolleeBLRespose enrolleeBLRespose = EnrolleeBLRespose.builder()
//			.BenPkgList(getWSBenefitPackages(wsEnrolleeBPBLResponse.getBusinessLevels()));
				.BusinessLevelCount(wsEnrolleeBPBLResponse.getBusinessLevelCount())
				.businessLevels(wsEnrolleeBPBLResponse.getBusinessLevels().stream().map(this::transformWSBPBusinessLevelsForBusinessLevels).collect(Collectors.toList()))
				.divisionNumber(wsEnrolleeBPBLResponse.getDivisionNumber())
				.errorMessage(wsEnrolleeBPBLResponse.getErrorMessage())
				.groupNumber(wsEnrolleeBPBLResponse.getGroupNumber()).memberType(wsEnrolleeBPBLResponse.getMemberType())
				.providerId(wsEnrolleeBPBLResponse.getProviderId()).statusCode(wsEnrolleeBPBLResponse.getStatusCode())
				.build();
		log.info("END PCPSearchResponseTransformer.transformEnrolleeBLRespose");
		return enrolleeBLRespose;
	}

	private EnrolleeBPBLResponse transformEnrolleeBPBLResponse(com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse wsEnrolleeBPBLResponse) {
		log.info("START PCPSearchResponseTransformer.transformEnrolleeBPBLResponse");
		EnrolleeBPBLResponse enrolleeBLRespose = EnrolleeBPBLResponse.builder()
//			.BenPkgList(getWSBenefitPackages(wsEnrolleeBPBLResponse.getBusinessLevels()));
				.BusinessLevelCount(wsEnrolleeBPBLResponse.getBusinessLevelCount())
				.businessLevels(wsEnrolleeBPBLResponse.getBusinessLevels().stream().map(this::transformBPBusinessLevelsForBPBusinessLevels).collect(Collectors.toList()))
				.divisionNumber(wsEnrolleeBPBLResponse.getDivisionNumber())
				.errorMessage(wsEnrolleeBPBLResponse.getErrorMessage())
				.groupNumber(wsEnrolleeBPBLResponse.getGroupNumber())
				.memberType(wsEnrolleeBPBLResponse.getMemberType())
				.providerId(wsEnrolleeBPBLResponse.getProviderId())
				.statusCode(wsEnrolleeBPBLResponse.getStatusCode())
				.build();
		log.info("END PCPSearchResponseTransformer.transformEnrolleeBPBLResponse");
		return enrolleeBLRespose;
	}

	private PCPAssignmentResponse getPCPAssignmentResponse(PcpAssignmentResponse clientPcpAssignmentResponse) {
		List<PcpResponse> clientPcpResponses = clientPcpAssignmentResponse.getPcpResponses();
		List<PCPResponse> pcpResponses = clientPcpResponses.stream().map(this::transformPCPResponse).collect(Collectors.toList());
		PCPAssignmentResponse pcpAssignmentResponse = PCPAssignmentResponse.builder()
				.processStatusCode(clientPcpAssignmentResponse.getProcessStatusCode())
				.processStatusDescription(clientPcpAssignmentResponse.getProcessStatusDescription())
				.sourceSystem(clientPcpAssignmentResponse.getSourceSystem())
				.pcpResponses(pcpResponses)
				.build();
		return pcpAssignmentResponse;
	}

	private PCPResponse transformPCPResponse(PcpResponse clientPcpRespnse) {
		log.info("START PCPSearchResponseTransformer.transformPCPResponse");
		PCPResponse pcpResponse = PCPResponse.builder().contractID(clientPcpRespnse.getContractID())
				.enrollees(clientPcpRespnse.getEnrollees().stream().map(this::transformEnrolleeDetail).collect(Collectors.toList()))
				.build();
		log.info("END PCPSearchResponseTransformer.transformPCPResponse");
		return pcpResponse;
	}

	private EnrolleeDetail transformEnrolleeDetail(com.deltadental.platform.pcp.stub.EnrolleeDetail clientEnrolleeDetail) {
		log.info("START PCPSearchResponseTransformer.transformEnrolleeDetail");
		EnrolleeDetail enrolleeDetail = EnrolleeDetail.builder()
				.benefitpackages(transformWSBenefitPackages(clientEnrolleeDetail.getBenefitpackages()))
				.businesslevels(clientEnrolleeDetail.getBusinesslevels().stream().map(this::transformBusinessLevels).collect(Collectors.toList()))
				.divisionNumber(clientEnrolleeDetail.getDivisionNumber())
				.enrolleeStatusCode(clientEnrolleeDetail.getEnrolleeStatusCode())
				.errorMessages(clientEnrolleeDetail.getErrorMessages())
				.groupNumber(clientEnrolleeDetail.getGroupNumber()).memberType(clientEnrolleeDetail.getMemberType())
				.numberOfFacilities(clientEnrolleeDetail.getNumberOfFacilities())
				.pcpproviders(clientEnrolleeDetail.getPcpproviders().stream().map(this::transformPcpProvider).collect(Collectors.toList()))
				.pcpValidationFlag(clientEnrolleeDetail.isPcpValidationFlag())
				.recordIdentifier(clientEnrolleeDetail.getRecordIdentifier())
				.build();
		log.info("END PCPSearchResponseTransformer.transformEnrolleeDetail");
		return enrolleeDetail;
	}

	private PcpProvider transformPcpProvider(com.deltadental.platform.pcp.stub.PcpProvider clientPcpProvider) {
		log.info("START PCPSearchResponseTransformer.transformPcpProvider");
		PcpProvider pcpprovider = PcpProvider.builder()
				.businesslevels(clientPcpProvider.getBusinesslevels().stream().map(this::transformBusinessLevels).collect(Collectors.toList()))
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

	private Address transformProviderAddress(com.deltadental.platform.pcp.stub.Address providerAddress) {
		log.info("START PCPSearchResponseTransformer.transformProviderAddress");
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

	private OfficeHour transformOfficeHours(com.deltadental.platform.pcp.stub.OfficeHour officeHours) {
		log.info("START PCPSearchResponseTransformer.transformOfficeHours");
		OfficeHour oh = OfficeHour.builder().officeHours(officeHours.getOfficeHours()).build();
		log.info("END PCPSearchResponseTransformer.transformOfficeHours");
		return oh;
	}

	private BusinessLevels transformBusinessLevels(com.deltadental.platform.pcp.stub.BusinessLevels wsBl) {
		log.info("START PCPSearchResponseTransformer.transformBusinessLevels");
		BusinessLevels domainBl = BusinessLevels.builder()
				.businessLevel4(wsBl.getBusinessLevel4())
				.businessLevel5(wsBl.getBusinessLevel5())
				.businessLevel6(wsBl.getBusinessLevel6())
				.businessLevel7(wsBl.getBusinessLevel7())
				.effectiveDate(wsBl.getEffectiveDate())
				.endDate(wsBl.getEndDate())
				.networkId(wsBl.getNetworkId())
				.classCode(wsBl.getClassCode())
				.build();
		log.info("END PCPSearchResponseTransformer.transformBusinessLevels");
		return domainBl;
	}

	private BusinessLevels transformWSBPBusinessLevelsForBusinessLevels(BpBusinessLevels wsBpBl) {
		log.info("START PCPSearchResponseTransformer.transformWSBPBusinessLevelsForBusinessLevels");
		BusinessLevels domainBl = BusinessLevels.builder()
				.businessLevel4(wsBpBl.getBusinessLevel4())
				.businessLevel5(wsBpBl.getBusinessLevel5())
				.businessLevel6(wsBpBl.getBusinessLevel6())
				.businessLevel7(wsBpBl.getBusinessLevel7())
				.effectiveDate(wsBpBl.getEffectiveDate())
				.endDate(wsBpBl.getEndDate())
				.networkId(wsBpBl.getNetworkId())
				.build();
		log.info("END PCPSearchResponseTransformer.transformWSBPBusinessLevelsForBusinessLevels");
		return domainBl;
	}

	private BPBusinessLevels transformBPBusinessLevelsForBPBusinessLevels(BpBusinessLevels wsBpBl) {
		log.info("START PCPSearchResponseTransformer.transformBPBusinessLevelsForBPBusinessLevels");
		BPBusinessLevels domainBpBl = BPBusinessLevels.builder()
				.benPkg(transformBenefitPackage(wsBpBl.getBenPkg()))
				.businessLevel4(wsBpBl.getBusinessLevel4())
				.businessLevel5(wsBpBl.getBusinessLevel5())
				.businessLevel6(wsBpBl.getBusinessLevel6())
				.businessLevel7(wsBpBl.getBusinessLevel7())
				.effectiveDate(wsBpBl.getEffectiveDate())
				.endDate(wsBpBl.getEndDate())
				.networkId(wsBpBl.getEndDate())
				.build();
		log.info("END PCPSearchResponseTransformer.transformBPBusinessLevelsForBPBusinessLevels");
		return domainBpBl;
	}

	private BenefitPackage transformBenefitPackage(com.deltadental.platform.pcp.stub.BenefitPackage stubBp) {
		log.info("START PCPSearchResponseTransformer.transformBenefitPackage");
		BenefitPackage bp = BenefitPackage.builder()
				.benefitPackageId(stubBp.getBenefitPackageId())
				.bpideffectiveDate(stubBp.getBpideffectiveDate())
				.bpidendDate(stubBp.getBpidendDate())
				.build();
		log.info("END PCPSearchResponseTransformer.transformBenefitPackage");
		return bp;
	}

	private List<BenefitPackage> transformWSBenefitPackages(List<com.deltadental.platform.pcp.stub.BenefitPackage> benefitpackages) {
		log.info("START PCPSearchResponseTransformer.transformWSBenefitPackages");
		List<BenefitPackage> domainsBps = benefitpackages.stream().map(this::transformBenefitPackage).collect(Collectors.toList());
		log.info("END PCPSearchResponseTransformer.transformWSBenefitPackages");
		return domainsBps;
	}

}
