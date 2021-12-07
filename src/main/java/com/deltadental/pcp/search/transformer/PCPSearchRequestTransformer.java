package com.deltadental.pcp.search.transformer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.BlServiceRequest;
import com.deltadental.pcp.search.domain.BusinessLevels;
import com.deltadental.pcp.search.domain.FacilitySearchRequest;
import com.deltadental.pcp.search.domain.PCPBLEnrollee;
import com.deltadental.pcp.search.domain.PCPRefineSearch;
import com.deltadental.pcp.search.domain.PCPRequest;
import com.deltadental.pcp.search.domain.PcpAssignmentRequest;
import com.deltadental.pcp.search.domain.PrimaryEnrolleePCPInfo;
import com.deltadental.pcp.search.domain.StatePcpAssignmentRequest;
import com.deltadental.platform.pcp.stub.FacilitySearch;
import com.deltadental.platform.pcp.stub.FacilityType;
import com.deltadental.platform.pcp.stub.Facilityregion;
import com.deltadental.platform.pcp.stub.Facilitystatus;
import com.deltadental.platform.pcp.stub.GetBussinessLevels;
import com.deltadental.platform.pcp.stub.GetProviders;
import com.deltadental.platform.pcp.stub.GetStatePCPAssignment;
import com.deltadental.platform.pcp.stub.GroupBenefitPackBussinessLevel;
import com.deltadental.platform.pcp.stub.PcpRefineSearch;
import com.deltadental.platform.pcp.stub.PcpRequest;
import com.deltadental.platform.pcp.stub.PcpblEnrollee;
import com.deltadental.platform.pcp.stub.ProviderValidate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PCPSearchRequestTransformer {

	public GetProviders transformPcpAssignmentRequest(PcpAssignmentRequest pcpAssignmentRequest) {
		GetProviders getProviders = new GetProviders();
		com.deltadental.platform.pcp.stub.PcpAssignmentRequest assignmentRequest = getPcpAssignmentRequest(pcpAssignmentRequest);
		getProviders.setArg0(assignmentRequest);
		return getProviders;
	}
	
	public FacilitySearch transformFacilitySearchRequest(FacilitySearchRequest facilitySearchRequest) {
		com.deltadental.platform.pcp.stub.FacilitySearchRequest searchRequest = new com.deltadental.platform.pcp.stub.FacilitySearchRequest();
		searchRequest.setBenefitpackageID(facilitySearchRequest.getBenefitpackageID());
		searchRequest.setDivisionNumber(facilitySearchRequest.getDivisionNumber());
		searchRequest.setEnrolleeZipCode(facilitySearchRequest.getEnrolleeZipCode());
		searchRequest.setGroupNumber(facilitySearchRequest.getGroupNumber());
		searchRequest.setMtvPersonID(facilitySearchRequest.getMtvPersonID());
		searchRequest.setProduct(facilitySearchRequest.getProduct());
		searchRequest.setFacilityregion(transformFacilityregion(facilitySearchRequest.getFacilityregion()));
		searchRequest.setFacilitystatus(transformFacilityregion(facilitySearchRequest.getFacilitystatus()));
		searchRequest.setFacilityType(transformFacilityregion(facilitySearchRequest.getFacilityType()));
		FacilitySearch facilitySearch = new FacilitySearch();
		facilitySearch.setArg0(searchRequest);
		return facilitySearch;
	}
	
	public GetBussinessLevels transformBlServiceRequest(BlServiceRequest blServiceRequest) {
		GetBussinessLevels getBussinessLevels = new GetBussinessLevels();
		com.deltadental.platform.pcp.stub.BlServiceRequest stubBlServiceRequest = getBlServiceRequest(blServiceRequest);
		getBussinessLevels.setArg0(stubBlServiceRequest);
		return getBussinessLevels;
	}
	
	public GetStatePCPAssignment transformStatePCPAssignmentRequest(StatePcpAssignmentRequest statePcpAssignmentRequest) {
		GetStatePCPAssignment getStatePCPAssignment = new GetStatePCPAssignment();
		transformStatePcpAssignmentRequest(statePcpAssignmentRequest, getStatePCPAssignment);
		return getStatePCPAssignment;
	}

	public GroupBenefitPackBussinessLevel transferGroupBenefitPackBussinessLevel(BlServiceRequest blServiceRequest) {
		GroupBenefitPackBussinessLevel groupBenefitPackBussinessLevel = new GroupBenefitPackBussinessLevel();
		com.deltadental.platform.pcp.stub.BlServiceRequest stubBlServiceRequest = getBlServiceRequest(blServiceRequest);
		groupBenefitPackBussinessLevel.setArg0(stubBlServiceRequest);
		return groupBenefitPackBussinessLevel;
	}
	
	public ProviderValidate transformProviderValidate(PcpAssignmentRequest pcpAssignmentRequest) {
		ProviderValidate providerValidate = new ProviderValidate();
		providerValidate.setArg0(getPcpAssignmentRequest(pcpAssignmentRequest));
		return providerValidate;
	}
	
	private void transformStatePcpAssignmentRequest(StatePcpAssignmentRequest statePcpAssignmentRequest, GetStatePCPAssignment getStatePCPAssignment) {
		com.deltadental.platform.pcp.stub.StatePcpAssignmentRequest stubStatePcpAssignmentRequest = new com.deltadental.platform.pcp.stub.StatePcpAssignmentRequest();
		stubStatePcpAssignmentRequest.setDependentStateCode(statePcpAssignmentRequest.getDependentStateCode());
		stubStatePcpAssignmentRequest.setDivisionNumber(statePcpAssignmentRequest.getDivisionNumber());
		stubStatePcpAssignmentRequest.setEnrolleeNumber(statePcpAssignmentRequest.getEnrolleeNumber());
		stubStatePcpAssignmentRequest.setGroupNumber(statePcpAssignmentRequest.getGroupNumber());
		stubStatePcpAssignmentRequest.setSourceSystem(statePcpAssignmentRequest.getSourceSystem());
		getStatePCPAssignment.setArg0(stubStatePcpAssignmentRequest);
	}
	
	private FacilityType transformFacilityregion(com.deltadental.pcp.search.domain.FacilityType facilityType) {
		FacilityType stubFacilityType = new FacilityType();
		stubFacilityType.setProviderspcialityDesc(facilityType.getProviderspcialityDesc());
		stubFacilityType.setProviderspecialityCode(facilityType.getProviderspecialityCode());
		stubFacilityType.setProviderType(facilityType.getProviderType());
		return stubFacilityType;
	}

	private Facilitystatus transformFacilityregion(com.deltadental.pcp.search.domain.Facilitystatus facilitystatus) {
		Facilitystatus stubFacilitystatus = new Facilitystatus();
		stubFacilitystatus.setEnrollStatus(facilitystatus.getEnrollStatus());
		stubFacilitystatus.setFacilitystatusType(facilitystatus.getFacilitystatusType());
		stubFacilitystatus.setFacilityType(facilitystatus.getFacilityType());
		stubFacilitystatus.setStatus(facilitystatus.getStatus());
		return stubFacilitystatus;
	}

	private Facilityregion transformFacilityregion(com.deltadental.pcp.search.domain.Facilityregion facilityregion) {
		Facilityregion stubFacilityregion = new Facilityregion();
		stubFacilityregion.setFacilityCity(facilityregion.getFacilityCity());
		stubFacilityregion.setFacilityState(facilityregion.getFacilityState());
		stubFacilityregion.setFacilityZip(facilityregion.getFacilityZip());
		return stubFacilityregion;
	}

	private com.deltadental.platform.pcp.stub.PcpAssignmentRequest getPcpAssignmentRequest(PcpAssignmentRequest pcpAssignmentRequest) {
		com.deltadental.platform.pcp.stub.PcpAssignmentRequest assignmentRequest = new com.deltadental.platform.pcp.stub.PcpAssignmentRequest();
		List<PCPRequest> pcpRequests = pcpAssignmentRequest.getPcpRequests();
		List<PcpRequest> stubPcpRequests = assignmentRequest.getPcpRequests();
		pcpRequests.forEach(pcpRequest -> {
			PcpRequest stubPcpRequest = new PcpRequest();
			stubPcpRequest.setAutoAssignmentFlag(pcpRequest.isAutoAssignmentFlag());
			stubPcpRequest.setContractID(pcpRequest.getContractID());
			stubPcpRequest.setPcpRefineSearch(getPcpRefineSearch(pcpRequest.getPcpRefineSearch()));
			stubPcpRequest.setPrimaryEnrolleePCPInfo(getPrimaryEnrolleePCPInfo(pcpRequest.getPrimaryEnrolleePCPInfo()));
			stubPcpRequests.add(stubPcpRequest);
		});
		assignmentRequest.setProduct(pcpAssignmentRequest.getProduct());
		assignmentRequest.setSourceSystem(pcpAssignmentRequest.getSourceSystem());
		return assignmentRequest;
	}
	
	private PcpRefineSearch getPcpRefineSearch(PCPRefineSearch pcpRefineSearch) {
		PcpRefineSearch refineSearch = new PcpRefineSearch();
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
		return refineSearch;
	}
	
	private  com.deltadental.platform.pcp.stub.PrimaryEnrolleePCPInfo getPrimaryEnrolleePCPInfo(PrimaryEnrolleePCPInfo primaryEnrolleePCPInfo) {
		com.deltadental.platform.pcp.stub.PrimaryEnrolleePCPInfo enrolleePCPInfo = new com.deltadental.platform.pcp.stub.PrimaryEnrolleePCPInfo();
		enrolleePCPInfo.setPeAddress(getAddress(primaryEnrolleePCPInfo.getPeAddress()));
		enrolleePCPInfo.setPeBusinessLevels(getBusinessLevels(primaryEnrolleePCPInfo.getPeBusinessLevels()));
		enrolleePCPInfo.setPePCPIdentifier(primaryEnrolleePCPInfo.getPePCPIdentifier());
		return enrolleePCPInfo;
	}
	
	private com.deltadental.platform.pcp.stub.BusinessLevels getBusinessLevels(BusinessLevels businessLevels) {
		com.deltadental.platform.pcp.stub.BusinessLevels peBusinessLevels = new com.deltadental.platform.pcp.stub.BusinessLevels();
		peBusinessLevels.setBusinessLevel4(businessLevels.getBusinessLevel4());
		peBusinessLevels.setBusinessLevel5(businessLevels.getBusinessLevel5());
		peBusinessLevels.setBusinessLevel6(businessLevels.getBusinessLevel6());
		peBusinessLevels.setBusinessLevel7(businessLevels.getBusinessLevel7());
		peBusinessLevels.setClassCode(businessLevels.getClassCode());
		peBusinessLevels.setEffectiveDate(businessLevels.getEffectiveDate());
		peBusinessLevels.setEndDate(businessLevels.getEndDate());
		peBusinessLevels.setNetworkId(businessLevels.getNetworkId());
		return peBusinessLevels;
	}
	
	private com.deltadental.platform.pcp.stub.Address getAddress(Address address) {
		com.deltadental.platform.pcp.stub.Address peAddress = new com.deltadental.platform.pcp.stub.Address();
		peAddress.setAddressLine1(address.getAddressLine1());
		peAddress.setAddressLine2(address.getAddressLine2());
		peAddress.setCity(address.getCity());
		peAddress.setState(address.getState());
		peAddress.setZipCode(address.getZipCode());
		return peAddress;
	}

	private PcpblEnrollee transformPcpblEnrollee(PCPBLEnrollee pcpblEnrollee) {
		PcpblEnrollee stubPcpblEnrollee = new PcpblEnrollee();
		stubPcpblEnrollee.setDivisionNumber(pcpblEnrollee.getDivisionNumber());
		stubPcpblEnrollee.setGroupNumber(pcpblEnrollee.getGroupNumber());
		stubPcpblEnrollee.setMemberLanguage(pcpblEnrollee.getMemberLanguage());
		stubPcpblEnrollee.setMemberType(pcpblEnrollee.getMemberType());
		stubPcpblEnrollee.setMemberAddress(getAddress(pcpblEnrollee.getMemberAddress()));		
		stubPcpblEnrollee.setMtvPersonID(pcpblEnrollee.getMtvPersonID());
		stubPcpblEnrollee.setPcpEffectiveDate(pcpblEnrollee.getPcpEffectiveDate());
		stubPcpblEnrollee.setPcpEndDate(pcpblEnrollee.getPcpEndDate());
		stubPcpblEnrollee.setPcpIdentifier(pcpblEnrollee.getPcpIdentifier());
		stubPcpblEnrollee.setPracticeLocation(pcpblEnrollee.getPracticeLocation());
		stubPcpblEnrollee.setRecordIdentifier(pcpblEnrollee.getRecordIdentifier());
		return stubPcpblEnrollee;
	}

	private com.deltadental.platform.pcp.stub.BlServiceRequest getBlServiceRequest(BlServiceRequest blServiceRequest) {
		com.deltadental.platform.pcp.stub.BlServiceRequest stubBlServiceRequest = new com.deltadental.platform.pcp.stub.BlServiceRequest();
		stubBlServiceRequest.setRouting(blServiceRequest.getRouting());
		stubBlServiceRequest.setSourceSystem(blServiceRequest.getSourceSystem());
		List<PCPBLEnrollee> pcpEnrolleeList = blServiceRequest.getPcpEnrollee();
		List<PcpblEnrollee> stubPcpEnrolleeList = stubBlServiceRequest.getPcpEnrollee();
		pcpEnrolleeList.forEach(pcpEnrollee -> {
			PcpblEnrollee stubPcpblEnrollee = transformPcpblEnrollee(pcpEnrollee);
			stubPcpEnrolleeList.add(stubPcpblEnrollee);
		});
		return stubBlServiceRequest;
	}

}
