package com.deltadental.pcp.search.transformer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.BlServiceRequest;
import com.deltadental.pcp.search.domain.BusinessLevels;
import com.deltadental.pcp.search.domain.FacilitySearchRequest;
import com.deltadental.pcp.search.domain.PCPBLEnrollee;
import com.deltadental.pcp.search.domain.PCPEnrollee;
import com.deltadental.pcp.search.domain.PCPRefineSearch;
import com.deltadental.pcp.search.domain.PCPRequest;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.PrimaryEnrolleePCPInfo;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.StatePcpAssignmentRequest;
import com.deltadental.platform.pcp.stub.FacilitySearch;
import com.deltadental.platform.pcp.stub.FacilityType;
import com.deltadental.platform.pcp.stub.Facilityregion;
import com.deltadental.platform.pcp.stub.Facilitystatus;
import com.deltadental.platform.pcp.stub.GetBussinessLevels;
import com.deltadental.platform.pcp.stub.GetProviders;
import com.deltadental.platform.pcp.stub.GetStatePCPAssignment;
import com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevel;
import com.deltadental.platform.pcp.stub.PcpEnrollee;
import com.deltadental.platform.pcp.stub.PcpProviderRequest;
import com.deltadental.platform.pcp.stub.PcpRefineSearch;
import com.deltadental.platform.pcp.stub.PcpRequest;
import com.deltadental.platform.pcp.stub.PcpSearchRequest;
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.PcpblEnrollee;
import com.deltadental.platform.pcp.stub.ProviderValidate;
import com.deltadental.platform.pcp.stub.Providers;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PCPSearchRequestTransformer {

	public GetProviders transformGetProviders(PcpAssignmentRequest pcpAssignmentRequest) {
		log.info("START PCPSearchRequestTransformer.transformGetProviders");
		GetProviders getProviders = new GetProviders();
		com.deltadental.platform.pcp.stub.PcpAssignmentRequest assignmentRequest = getPcpAssignmentRequest(pcpAssignmentRequest);
		getProviders.setArg0(assignmentRequest);
		log.info("END PCPSearchRequestTransformer.transformGetProviders");
		return getProviders;
	}
	
	public FacilitySearch transformFacilitySearch(FacilitySearchRequest facilitySearchRequest) {
		log.info("START PCPSearchRequestTransformer.transformFacilitySearch");
		com.deltadental.platform.pcp.stub.FacilitySearchRequest searchRequest = new com.deltadental.platform.pcp.stub.FacilitySearchRequest();
		searchRequest.setBenefitpackageID(facilitySearchRequest.getBenefitPackageID());
		searchRequest.setBusinesslevel(getBusinessLevels(facilitySearchRequest.getBusinessLevels()));
		searchRequest.setDivisionNumber(facilitySearchRequest.getDivisionNumber());
		searchRequest.setEnrolleeZipCode(facilitySearchRequest.getEnrolleeZipCode());
		searchRequest.setGroupNumber(facilitySearchRequest.getGroupNumber());
		searchRequest.setMtvPersonID(facilitySearchRequest.getMtvPersonID());
		searchRequest.setProduct(facilitySearchRequest.getProduct());
		searchRequest.setFacilityregion(transformFacilityregion(facilitySearchRequest.getFacilityRegion()));
		searchRequest.setFacilitystatus(transformFacilityregion(facilitySearchRequest.getFacilityStatus()));
		searchRequest.setFacilityType(transformFacilityregion(facilitySearchRequest.getFacilityType()));
		FacilitySearch facilitySearch = new FacilitySearch();
		facilitySearch.setArg0(searchRequest);
		log.info("END PCPSearchRequestTransformer.transformFacilitySearch");
		return facilitySearch;
	}
	
	public GetBussinessLevels transformGetBussinessLevels(BlServiceRequest blServiceRequest) {
		log.info("START PCPSearchRequestTransformer.transformGetBussinessLevels");
		GetBussinessLevels getBussinessLevels = new GetBussinessLevels();
		com.deltadental.platform.pcp.stub.BlServiceRequest stubBlServiceRequest = getBlServiceRequest(blServiceRequest);
		getBussinessLevels.setArg0(stubBlServiceRequest);
		log.info("END PCPSearchRequestTransformer.transformGetBussinessLevels");
		return getBussinessLevels;
	}
	
	public GetStatePCPAssignment transformGetStatePCPAssignment(StatePcpAssignmentRequest statePcpAssignmentRequest) {
		log.info("START PCPSearchRequestTransformer.transformGetStatePCPAssignment");
		GetStatePCPAssignment getStatePCPAssignment = new GetStatePCPAssignment();
		com.deltadental.platform.pcp.stub.StatePcpAssignmentRequest stubStatePcpAssignmentRequest = new com.deltadental.platform.pcp.stub.StatePcpAssignmentRequest();
		stubStatePcpAssignmentRequest.setDependentStateCode(statePcpAssignmentRequest.getDependentStateCode());
		stubStatePcpAssignmentRequest.setDivisionNumber(statePcpAssignmentRequest.getDivisionNumber());
		stubStatePcpAssignmentRequest.setEnrolleeNumber(statePcpAssignmentRequest.getEnrolleeNumber());
		stubStatePcpAssignmentRequest.setGroupNumber(statePcpAssignmentRequest.getGroupNumber());
		stubStatePcpAssignmentRequest.setSourceSystem(statePcpAssignmentRequest.getSourceSystem());
		getStatePCPAssignment.setArg0(stubStatePcpAssignmentRequest);
		log.info("END PCPSearchRequestTransformer.transformGetStatePCPAssignment");
		return getStatePCPAssignment;
	}

	public GroupBenefitPackBussinessLevel transformGroupBenefitPackBussinessLevel(BlServiceRequest blServiceRequest) {
		log.info("START PCPSearchRequestTransformer.transformGroupBenefitPackBussinessLevel");
		GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel = new GroupBenefitPackBussinessLevel();
		com.deltadental.platform.pcp.stub.BlServiceRequest stubBlServiceRequest = getBlServiceRequest(blServiceRequest);
		groupBenefitPackBussinessLevel.setArg0(stubBlServiceRequest);
		log.info("END PCPSearchRequestTransformer.transformGroupBenefitPackBussinessLevel");
		return groupBenefitPackBussinessLevel;
	}
	
	public ProviderValidate transformProviderValidate(PcpAssignmentRequest pcpAssignmentRequest) {
		log.info("START PCPSearchRequestTransformer.transformProviderValidate");
		ProviderValidate providerValidate = new ProviderValidate();
		providerValidate.setArg0(getPcpAssignmentRequest(pcpAssignmentRequest));
		log.info("END PCPSearchRequestTransformer.transformProviderValidate");
		return providerValidate;
	}
	
	public Providers transformProvidersRequest(ProvidersRequest providersRequest) {
		log.info("START PCPSearchRequestTransformer.transformProviderValidate");
		Providers providers = new Providers();
		PcpSearchRequest pcpSearchRequest = new PcpSearchRequest();
		pcpSearchRequest.setContractID(providersRequest.getContractID());
		pcpSearchRequest.setMemberId(providersRequest.getMemberId());
		pcpSearchRequest.setPcpEffectiveDate(providersRequest.getPcpEffectiveDate());
		pcpSearchRequest.setZipcode(providersRequest.getZipcode());
		pcpSearchRequest.setSourceSystem(providersRequest.getSourceSystem());
		pcpSearchRequest.setAddress(getAddress(providersRequest.getAddress()));
		providers.setArg0(pcpSearchRequest);
		log.info("START PCPSearchRequestTransformer.transformProviderValidate");
		return providers;
	}
	
	private FacilityType transformFacilityregion(com.deltadental.pcp.search.domain.FacilityType facilityType) {
		log.info("START PCPSearchRequestTransformer.transformFacilityregion");
		FacilityType stubFacilityType = new FacilityType();
		if(facilityType != null) {
			stubFacilityType.setProviderspcialityDesc(facilityType.getProviderSpcialityDesc());
			stubFacilityType.setProviderspecialityCode(facilityType.getProviderSpecialityCode());
			stubFacilityType.setProviderType(facilityType.getProviderType());
		}
		log.info("END PCPSearchRequestTransformer.transformFacilityregion");
		return stubFacilityType;
	}

	private Facilitystatus transformFacilityregion(com.deltadental.pcp.search.domain.Facilitystatus facilitystatus) {
		log.info("START PCPSearchRequestTransformer.transformFacilityregion");
		Facilitystatus stubFacilitystatus = new Facilitystatus();
		if(facilitystatus != null) {			
			stubFacilitystatus.setEnrollStatus(facilitystatus.getEnrollStatus());
			stubFacilitystatus.setFacilitystatusType(facilitystatus.getFacilityStatusType());
			stubFacilitystatus.setFacilityType(facilitystatus.getFacilityType());
			stubFacilitystatus.setStatus(facilitystatus.getStatus());			
		}
		log.info("END PCPSearchRequestTransformer.transformFacilityregion");
		return stubFacilitystatus;
	}

	private Facilityregion transformFacilityregion(com.deltadental.pcp.search.domain.Facilityregion facilityregion) {
		log.info("START PCPSearchRequestTransformer.transformFacilityregion");
		Facilityregion stubFacilityregion = new Facilityregion();
		if(facilityregion != null) {
			stubFacilityregion.setFacilityCity(facilityregion.getFacilityCity());
			stubFacilityregion.setFacilityState(facilityregion.getFacilityState());
			stubFacilityregion.setFacilityZip(facilityregion.getFacilityZip());
		}
		log.info("END PCPSearchRequestTransformer.transformFacilityregion");
		return stubFacilityregion;
	}

	private com.deltadental.platform.pcp.stub.PcpAssignmentRequest getPcpAssignmentRequest(PcpAssignmentRequest pcpAssignmentRequest) {
		log.info("START PCPSearchRequestTransformer.getPcpAssignmentRequest");
		com.deltadental.platform.pcp.stub.PcpAssignmentRequest assignmentRequest = new com.deltadental.platform.pcp.stub.PcpAssignmentRequest();
		List<PCPRequest> pcpRequests = pcpAssignmentRequest.getPcpRequests();
		List<PcpRequest> stubPcpRequests = assignmentRequest.getPcpRequests();
		pcpRequests.forEach(pcpRequest -> {
			PcpRequest stubPcpRequest = new PcpRequest();
			stubPcpRequest.setAutoAssignmentFlag(pcpRequest.isAutoAssignmentFlag());
			stubPcpRequest.setContractID(pcpRequest.getContractId());
			stubPcpRequest.setPcpRefineSearch(getPcpRefineSearch(pcpRequest.getPcpRefineSearch()));
			stubPcpRequest.setPrimaryEnrolleePCPInfo(getPrimaryEnrolleePCPInfo(pcpRequest.getPrimaryEnrolleePCPInfo()));
			List<PcpEnrollee> stubEnrollees = stubPcpRequest.getEnrollees();
			mapEnrolles(stubEnrollees, pcpRequest.getEnrollees());
			stubPcpRequests.add(stubPcpRequest);
		});
		assignmentRequest.setProduct(pcpAssignmentRequest.getProduct());
		assignmentRequest.setSourceSystem(pcpAssignmentRequest.getSourceSystem());
		log.info("END PCPSearchRequestTransformer.getPcpAssignmentRequest");
		return assignmentRequest;
	}
	
	private void mapEnrolles(List<PcpEnrollee> stubEnrollees, List<PCPEnrollee> enrollees) {
		log.info("START PCPSearchRequestTransformer.mapEnrolles");
		enrollees.forEach(enrollee -> {
			PcpEnrollee pcpEnrollee = new PcpEnrollee();
			pcpEnrollee.setAutoAssignmentState(enrollee.getAutoAssignmentState());
			pcpEnrollee.setBatchAutoAssignFlag(enrollee.isBatchAutoAssignFlag());
			pcpEnrollee.setBenefitPackageID(enrollee.getBenefitPackageID());
			pcpEnrollee.setBusinessLevel7(enrollee.getBusinessLevel7());
			pcpEnrollee.setCcaOverrideFlag(enrollee.isCcaOverrideFlag());
			pcpEnrollee.setDateofBirth(enrollee.getDateOfBirth());
			pcpEnrollee.setDivisionNumber(enrollee.getDivisionNumber());
			pcpEnrollee.setEnrolleeBusinessLevels(getBusinessLevels(enrollee.getEnrolleeBusinessLevels()));
			pcpEnrollee.setGroupInclusion(enrollee.getGroupInclusion());
			pcpEnrollee.setGroupNumber(enrollee.getGroupNumber());
			pcpEnrollee.setMassTransfer(enrollee.getMassTransfer());
			pcpEnrollee.setMemberAddress(getAddress(enrollee.getMemberAddress()));
			pcpEnrollee.setMemberLanguage(enrollee.getMemberLanguage());
			pcpEnrollee.setMemberType(enrollee.getMemberType());
			pcpEnrollee.setMtvPersonID(enrollee.getMtvPersonID());
			pcpEnrollee.setNetworkId(enrollee.getNetworkId());
			pcpEnrollee.setNewMember(enrollee.getNewMember());
			pcpEnrollee.setOverRideFlag(enrollee.getOverRideFlag());
			pcpEnrollee.setPcpEffectiveDate(enrollee.getPcpEffectiveDate());
			pcpEnrollee.setPcpEndDate(enrollee.getPcpEndDate());
			pcpEnrollee.setPcpIdentifier(enrollee.getPcpIdentifier());
			pcpEnrollee.setPracticeLocation(enrollee.getPracticeLocation());
			pcpEnrollee.setRandomSelection(enrollee.getRandomSelection());
			pcpEnrollee.setRecordIdentifier(enrollee.getRecordIdentifier());
			pcpEnrollee.setSourceSystem(enrollee.getSourceSystem());
			stubEnrollees.add(pcpEnrollee);
		});
		log.info("END PCPSearchRequestTransformer.mapEnrolles");
	}		

	private PcpRefineSearch getPcpRefineSearch(PCPRefineSearch pcpRefineSearch) {
		log.info("START PCPSearchRequestTransformer.getPcpRefineSearch");
		PcpRefineSearch refineSearch = new PcpRefineSearch();
		if(pcpRefineSearch != null) {
			refineSearch.setCity(pcpRefineSearch.getCity());
			refineSearch.setDistance(pcpRefineSearch.getDistance());
			refineSearch.setGender(pcpRefineSearch.getGender());
			refineSearch.setKeyword(pcpRefineSearch.getKeyword());
			refineSearch.setLanguage(pcpRefineSearch.getLanguage());
			refineSearch.setLocation(pcpRefineSearch.getLocation());
			refineSearch.setOfficeAccess(pcpRefineSearch.getOfficeAccess());
			refineSearch.setOfficeHours(pcpRefineSearch.getOfficeHours());
			refineSearch.setOthers(pcpRefineSearch.getOthers());
			refineSearch.setPatientConsideration(pcpRefineSearch.getPatientConsideration());
			refineSearch.setSortND(pcpRefineSearch.getSortND());
			refineSearch.setState(pcpRefineSearch.getState());
			refineSearch.setWorkMode(pcpRefineSearch.getWorkMode());
			refineSearch.setZipCode(pcpRefineSearch.getZipCode());
		}
		log.info("END PCPSearchRequestTransformer.getPcpRefineSearch");
		return refineSearch;
	}
	
	private  com.deltadental.platform.pcp.stub.PrimaryEnrolleePCPInfo getPrimaryEnrolleePCPInfo(PrimaryEnrolleePCPInfo primaryEnrolleePCPInfo) {
		log.info("START PCPSearchRequestTransformer.getPrimaryEnrolleePCPInfo");
		com.deltadental.platform.pcp.stub.PrimaryEnrolleePCPInfo enrolleePCPInfo = new com.deltadental.platform.pcp.stub.PrimaryEnrolleePCPInfo();
		if(primaryEnrolleePCPInfo != null) {
			enrolleePCPInfo.setPeAddress(getAddress(primaryEnrolleePCPInfo.getPeAddress()));
			enrolleePCPInfo.setPeBusinessLevels(getBusinessLevels(primaryEnrolleePCPInfo.getPeBusinessLevels()));
			enrolleePCPInfo.setPePCPIdentifier(primaryEnrolleePCPInfo.getPePCPIdentifier());
		}
		log.info("END PCPSearchRequestTransformer.getPrimaryEnrolleePCPInfo");
		return enrolleePCPInfo;
	}
	
	private com.deltadental.platform.pcp.stub.BusinessLevels getBusinessLevels(BusinessLevels businessLevels) {
		log.info("START PCPSearchRequestTransformer.getBusinessLevels");
		com.deltadental.platform.pcp.stub.BusinessLevels peBusinessLevels = new com.deltadental.platform.pcp.stub.BusinessLevels();
		if(businessLevels != null) {
			peBusinessLevels.setBusinessLevel4(businessLevels.getBusinessLevel4());
			peBusinessLevels.setBusinessLevel5(businessLevels.getBusinessLevel5());
			peBusinessLevels.setBusinessLevel6(businessLevels.getBusinessLevel6());
			peBusinessLevels.setBusinessLevel7(businessLevels.getBusinessLevel7());
			peBusinessLevels.setClassCode(businessLevels.getClassCode());
			peBusinessLevels.setEffectiveDate(businessLevels.getEffectiveDate());
			peBusinessLevels.setEndDate(businessLevels.getEndDate());
			peBusinessLevels.setNetworkId(businessLevels.getNetworkId());
		}
		log.info("END PCPSearchRequestTransformer.getBusinessLevels");
		return peBusinessLevels;
	}
	
	private com.deltadental.platform.pcp.stub.Address getAddress(Address address) {
		log.info("START PCPSearchRequestTransformer.getAddress");
		com.deltadental.platform.pcp.stub.Address peAddress = new com.deltadental.platform.pcp.stub.Address();
		if(address != null) {
			peAddress.setAddressLine1(address.getAddressLine1());
			peAddress.setAddressLine2(address.getAddressLine2());
			peAddress.setCity(address.getCity());
			peAddress.setState(address.getState());
			peAddress.setZipCode(address.getZipCode());
		}
		log.info("END PCPSearchRequestTransformer.getAddress");
		return peAddress;
	}

	private com.deltadental.platform.pcp.stub.BlServiceRequest getBlServiceRequest(BlServiceRequest blServiceRequest) {
		log.info("START PCPSearchRequestTransformer.getBlServiceRequest");
		com.deltadental.platform.pcp.stub.BlServiceRequest stubBlServiceRequest = new com.deltadental.platform.pcp.stub.BlServiceRequest();
		if(blServiceRequest != null) {
			stubBlServiceRequest.setRouting(blServiceRequest.getRouting());
			stubBlServiceRequest.setSourceSystem(blServiceRequest.getSourceSystem());
			List<PCPBLEnrollee> pcpEnrolleeList = blServiceRequest.getPcpEnrollee();
			List<PcpblEnrollee> stubPcpEnrolleeList = stubBlServiceRequest.getPcpEnrollee();
			pcpEnrolleeList.forEach(pcpEnrollee -> {
				PcpblEnrollee stubPcpblEnrollee = transformPcpblEnrollee(pcpEnrollee);
				stubPcpEnrolleeList.add(stubPcpblEnrollee);
			});
		}
		log.info("END PCPSearchRequestTransformer.getBlServiceRequest");
		return stubBlServiceRequest;
	}
	
	private PcpblEnrollee transformPcpblEnrollee(PCPBLEnrollee pcpblEnrollee) {
		log.info("START PCPSearchRequestTransformer.transformPcpblEnrollee");
		PcpblEnrollee stubPcpblEnrollee = new PcpblEnrollee();
		if(pcpblEnrollee != null) {
			stubPcpblEnrollee.setDivisionNumber(pcpblEnrollee.getDivisionNumber());
			stubPcpblEnrollee.setGroupNumber(pcpblEnrollee.getGroupNumber());
			stubPcpblEnrollee.setMemberLanguage(pcpblEnrollee.getMemberLanguage());
			stubPcpblEnrollee.setMemberType(pcpblEnrollee.getMemberType());
			stubPcpblEnrollee.setMemberAddress(getAddress(pcpblEnrollee.getMemberAddress()));		
			stubPcpblEnrollee.setMtvPersonID(pcpblEnrollee.getMtvPersonId());
			stubPcpblEnrollee.setPcpEffectiveDate(pcpblEnrollee.getPcpEffectiveDate());
			stubPcpblEnrollee.setPcpEndDate(pcpblEnrollee.getPcpEndDate());
			stubPcpblEnrollee.setPcpIdentifier(pcpblEnrollee.getPcpIdentifier());
			stubPcpblEnrollee.setPracticeLocation(pcpblEnrollee.getPracticeLocation());
			stubPcpblEnrollee.setRecordIdentifier(pcpblEnrollee.getRecordIdentifier());
		}
		log.info("END PCPSearchRequestTransformer.transformPcpblEnrollee");
		return stubPcpblEnrollee;
	}

	public PcpValidate transformPcpValidateRequest(PCPValidateRequest pcpValidateRequest) {
		log.info("START PCPSearchRequestTransformer.transformPcpValidateRequest");
		PcpValidate pcpValidate = new PcpValidate();
		if(pcpValidateRequest != null) {
			PcpProviderRequest pcpProviderRequest = new PcpProviderRequest();
			pcpProviderRequest.setContractId(pcpValidateRequest.getContractId());
			pcpProviderRequest.setLookAheadDays(pcpValidateRequest.getLookAheadDays());
			pcpProviderRequest.setMemberType(pcpValidateRequest.getMemberType());
			pcpProviderRequest.setMtvPersonId(pcpValidateRequest.getMtvPersonId());
			pcpProviderRequest.setPcpEffDate(pcpValidateRequest.getPcpEffDate());
			pcpProviderRequest.setPcpEndDate(pcpValidateRequest.getPcpEndDate());
			pcpProviderRequest.setProduct(pcpValidateRequest.getProduct());
			pcpProviderRequest.setProviderId(pcpValidateRequest.getProviderId());
			pcpProviderRequest.setRecordIdentifier(pcpValidateRequest.getRecordIdentifier());
			pcpProviderRequest.setSourceSystem(pcpValidateRequest.getSourceSystem());
			pcpValidate.setArg0(pcpProviderRequest);
		}
		
		log.info("END PCPSearchRequestTransformer.transformPcpValidateRequest");
		return pcpValidate;
	}

}
