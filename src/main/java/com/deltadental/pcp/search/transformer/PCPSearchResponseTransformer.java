package com.deltadental.pcp.search.transformer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
		PcpAssignmentResponse clientPcpAssignmentResponse = getProvidersResponse.getReturn();
		PCPAssignmentResponse pcpAssignmentResponse = getPCPAssignmentResponse(clientPcpAssignmentResponse);
		return pcpAssignmentResponse;
	}
	
	public FacilityResponse transformFaclilitySearchResponse(FacilitySearchResponse facilitySearchResponse) {
		FacilityResponse facilityResponse = new FacilityResponse();
		com.deltadental.platform.pcp.stub.FacilityResponse wsFacilityResponse =  facilitySearchResponse.getReturn();
		List<com.deltadental.platform.pcp.stub.Facility> wsFacilities = wsFacilityResponse.getFacility();
		List<Facility> domainFacilities = new ArrayList<>();
		wsFacilities.forEach(wsFacility -> {
			Facility domainFacility = new Facility();
			domainFacility.setAddressLine1(wsFacility.getAddressLine1());
			domainFacility.setAddressLine2(wsFacility.getAddressLine2());
			domainFacility.setAddressLine3(wsFacility.getAddressLine3());
			domainFacility.setCity(wsFacility.getCity());
			domainFacility.setContracted(wsFacility.getContracted());
			domainFacility.setEffectiveDate(wsFacility.getEffectiveDate());
			domainFacility.setEnrollStatus(wsFacility.getEffectiveDate());
			domainFacility.setFacilityID(wsFacility.getFacilityID());
			domainFacility.setFacilityName(wsFacility.getFacilityName());
			domainFacility.setFacilitystatus(wsFacility.getFacilitystatus());
			domainFacility.setPhoneNumber(wsFacility.getPhoneNumber());
			domainFacility.setSpecility(wsFacility.getSpecility());
			domainFacility.setState(wsFacility.getState());
			domainFacility.setZip(wsFacility.getZip());
			domainFacilities.add(domainFacility);
		});
		facilityResponse.setFacility(domainFacilities);
		return facilityResponse;
	}

	public StatePcpAssignmentResponse transformGetStatePCPAssignmentResponse(GetStatePCPAssignmentResponse getStatePCPAssignmentResponse) {
		StatePcpAssignmentResponse statePcpAssignmentResponse = new StatePcpAssignmentResponse();
		com.deltadental.platform.pcp.stub.StatePcpAssignmentResponse wsStatePcpAssignmentResponse = getStatePCPAssignmentResponse.getReturn();
		statePcpAssignmentResponse.setDivisionNumber(wsStatePcpAssignmentResponse.getDivisionNumber());
		statePcpAssignmentResponse.setGroupDivEligibleforAutoPcp(wsStatePcpAssignmentResponse.isGroupDivEligibleforAutoPcp());
		statePcpAssignmentResponse.setGroupNumber(wsStatePcpAssignmentResponse.getGroupNumber());
		statePcpAssignmentResponse.setReturnCode(wsStatePcpAssignmentResponse.getReturnCode());
		statePcpAssignmentResponse.setReturnDescription(wsStatePcpAssignmentResponse.getReturnDescription());
		statePcpAssignmentResponse.setState(wsStatePcpAssignmentResponse.getState());
		statePcpAssignmentResponse.setStateEligibleforPcp(wsStatePcpAssignmentResponse.isStateEligibleforPcp());
		statePcpAssignmentResponse.setUnlimitedSplitFamily_INDV_Flag(wsStatePcpAssignmentResponse.isUnlimitedSplitFamilyINDVFlag());
		statePcpAssignmentResponse.setUnlimitedSplitFamilyFlag(wsStatePcpAssignmentResponse.isUnlimitedSplitFamilyFlag());
		return statePcpAssignmentResponse;
	}

	public BlResolutionResponse transformStubGetBussinessLevelsResponse(GetBussinessLevelsResponse getBussinessLevelsResponse) {
		BlResolutionResponse blResolutionResponse = new BlResolutionResponse();
		com.deltadental.platform.pcp.stub.BlResolutionResponse wsBlResolutionResponse = getBussinessLevelsResponse.getReturn();
		List<EnrolleeBLRespose> domainEnrolleeList = new ArrayList<EnrolleeBLRespose>();
		List<com.deltadental.platform.pcp.stub.EnrolleeBLRespose> wsEnrolleeBLResposes =  wsBlResolutionResponse.getEnrollee();
		wsEnrolleeBLResposes.forEach(enrolleeBLRespose -> {
			EnrolleeBLRespose domainEnrollee = new EnrolleeBLRespose();
			domainEnrollee.setBusinessLevelCount(enrolleeBLRespose.getBusinessLevelCount());
			domainEnrollee.setBenPkgList(getWSBenefitPackages(enrolleeBLRespose.getBenPkgList()));
			domainEnrollee.setBusinessLevels(getWSBusinesslevels(enrolleeBLRespose.getBusinessLevels()));
			domainEnrollee.setDivisionNumber(enrolleeBLRespose.getDivisionNumber());
			domainEnrollee.setErrorMessage(enrolleeBLRespose.getErrorMessage());
			domainEnrollee.setGroupNumber(enrolleeBLRespose.getGroupNumber());
			domainEnrollee.setMemberType(enrolleeBLRespose.getMemberType());
			domainEnrollee.setProviderId(enrolleeBLRespose.getProviderId());
			domainEnrollee.setStatusCode(enrolleeBLRespose.getStatusCode());
			domainEnrolleeList.add(domainEnrollee);
		});
		blResolutionResponse.setEnrollee(domainEnrolleeList);
		return blResolutionResponse;
	}

	public BPBLResolutionResponse transformStubGroupBenefitPackBussinessLevelResponse(GroupBenefitPackBussinessLevelResponse groupBenefitPackBussinessLevelResponse) {
		BPBLResolutionResponse bpblResolutionResponse = new BPBLResolutionResponse(); 
		BpblResolutionResponse wsBpblResolutionResponse = groupBenefitPackBussinessLevelResponse.getReturn();
		List<com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse> wsEnrolleeBPBLResponses = wsBpblResolutionResponse.getEnrollee();
		List<EnrolleeBPBLResponse> domainEnrolleeBPBLResponses = getDomainEnrolleeBPBLResposeList(wsEnrolleeBPBLResponses);
		bpblResolutionResponse.setEnrollee(domainEnrolleeBPBLResponses);
		return bpblResolutionResponse;
	}

	public PCPAssignmentResponse transformStubProviderValidateResponse(ProviderValidateResponse providerValidateResponse) {
		PcpAssignmentResponse clientPcpAssignmentResponse = providerValidateResponse.getReturn();
		PCPAssignmentResponse pcpAssignmentResponse = getPCPAssignmentResponse(clientPcpAssignmentResponse);
		return pcpAssignmentResponse;
	}

	public BlResolutionResponse transformGetBPsAndBussinessLevelsResponse(GetBPsAndBussinessLevelsResponse getBPsAndBussinessLevelsResponse) {
		BlResolutionResponse blResolutionResponse = new BlResolutionResponse();
		BpblResolutionResponse wsBpblResolutionResponse = getBPsAndBussinessLevelsResponse.getReturn();
		List<com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse> wsEnrolleeBPBLResponseList = wsBpblResolutionResponse.getEnrollee();
		List<EnrolleeBLRespose> domainEnrolleeBLResponses = getDomainEnrolleeBLResposeList(wsEnrolleeBPBLResponseList);
		blResolutionResponse.setEnrollee(domainEnrolleeBLResponses);
		return blResolutionResponse;
	}

	private List<EnrolleeBLRespose> getDomainEnrolleeBLResposeList(List<com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse> wsEnrolleeBPBLResponseList) {
		List<EnrolleeBLRespose> domainEnrolleeBLResposeList = new ArrayList<EnrolleeBLRespose>();
		wsEnrolleeBPBLResponseList.forEach(wsEnrolleeBPBLResponse -> {
			EnrolleeBLRespose enrolleeBLRespose = new EnrolleeBLRespose();
//			enrolleeBLRespose.setBenPkgList(getWSBenefitPackages(wsEnrolleeBPBLResponse.getBusinessLevels()));
			enrolleeBLRespose.setBusinessLevelCount(wsEnrolleeBPBLResponse.getBusinessLevelCount());
			enrolleeBLRespose.setBusinessLevels(getWSBusinesslevelsFromBpBls(wsEnrolleeBPBLResponse.getBusinessLevels()));
			enrolleeBLRespose.setDivisionNumber(wsEnrolleeBPBLResponse.getDivisionNumber());
			enrolleeBLRespose.setErrorMessage(wsEnrolleeBPBLResponse.getErrorMessage());
			enrolleeBLRespose.setGroupNumber(wsEnrolleeBPBLResponse.getGroupNumber());
			enrolleeBLRespose.setMemberType(wsEnrolleeBPBLResponse.getMemberType());
			enrolleeBLRespose.setProviderId(wsEnrolleeBPBLResponse.getProviderId());
			enrolleeBLRespose.setStatusCode(wsEnrolleeBPBLResponse.getStatusCode());
			domainEnrolleeBLResposeList.add(enrolleeBLRespose);
		});
		return domainEnrolleeBLResposeList;
	}
	
	private List<EnrolleeBPBLResponse> getDomainEnrolleeBPBLResposeList(List<com.deltadental.platform.pcp.stub.EnrolleeBPBLResponse> wsEnrolleeBPBLResponseList) {
		List<EnrolleeBPBLResponse> domainEnrolleeBLResposeList = new ArrayList<EnrolleeBPBLResponse>();
		wsEnrolleeBPBLResponseList.forEach(wsEnrolleeBPBLResponse -> {
			EnrolleeBPBLResponse enrolleeBLRespose = new EnrolleeBPBLResponse();
//			enrolleeBLRespose.setBenPkgList(getWSBenefitPackages(wsEnrolleeBPBLResponse.getBusinessLevels()));
			enrolleeBLRespose.setBusinessLevelCount(wsEnrolleeBPBLResponse.getBusinessLevelCount());
			enrolleeBLRespose.setBusinessLevels(getWSBpBusinesslevels(wsEnrolleeBPBLResponse.getBusinessLevels()));
			enrolleeBLRespose.setDivisionNumber(wsEnrolleeBPBLResponse.getDivisionNumber());
			enrolleeBLRespose.setErrorMessage(wsEnrolleeBPBLResponse.getErrorMessage());
			enrolleeBLRespose.setGroupNumber(wsEnrolleeBPBLResponse.getGroupNumber());
			enrolleeBLRespose.setMemberType(wsEnrolleeBPBLResponse.getMemberType());
			enrolleeBLRespose.setProviderId(wsEnrolleeBPBLResponse.getProviderId());
			enrolleeBLRespose.setStatusCode(wsEnrolleeBPBLResponse.getStatusCode());
			domainEnrolleeBLResposeList.add(enrolleeBLRespose);
		});
		return domainEnrolleeBLResposeList;
	}
	
	private PCPAssignmentResponse getPCPAssignmentResponse(PcpAssignmentResponse clientPcpAssignmentResponse) {
		PCPAssignmentResponse pcpAssignmentResponse = new PCPAssignmentResponse();
		pcpAssignmentResponse.setProcessStatusCode(clientPcpAssignmentResponse.getProcessStatusCode());
		pcpAssignmentResponse.setProcessStatusDescription(clientPcpAssignmentResponse.getProcessStatusDescription());
		pcpAssignmentResponse.setSourceSystem(clientPcpAssignmentResponse.getSourceSystem());
		List<PcpResponse> clientPcpResponses = clientPcpAssignmentResponse.getPcpResponses();
		List<PCPResponse> pcpResponses = new ArrayList<>();
		clientPcpResponses.forEach(clientPcpRespnse -> {
			PCPResponse pcpResponse = new PCPResponse();
			pcpResponse.setContractID(clientPcpRespnse.getContractID());
			pcpResponse.setEnrollees(getEnrollees(clientPcpRespnse.getEnrollees()));
			pcpResponses.add(pcpResponse);
		});
		pcpAssignmentResponse.setPcpResponses(pcpResponses);
		return pcpAssignmentResponse;
	}

	private List<EnrolleeDetail> getEnrollees(List<com.deltadental.platform.pcp.stub.EnrolleeDetail> enrollees) {
		List<EnrolleeDetail> enrolleeDetails = new ArrayList<EnrolleeDetail>();
		enrollees.forEach(clientEnrolleeDetail -> {
			EnrolleeDetail enrolleeDetail = new EnrolleeDetail();
			enrolleeDetail.setBenefitpackages(getWSBenefitPackages(clientEnrolleeDetail.getBenefitpackages()));
			enrolleeDetail.setBusinesslevels(getWSBusinesslevels(clientEnrolleeDetail.getBusinesslevels()));
			enrolleeDetail.setDivisionNumber(clientEnrolleeDetail.getDivisionNumber());
			enrolleeDetail.setEnrolleeStatusCode(clientEnrolleeDetail.getEnrolleeStatusCode());
			enrolleeDetail.setErrorMessages(clientEnrolleeDetail.getErrorMessages());
			enrolleeDetail.setGroupNumber(clientEnrolleeDetail.getGroupNumber());
			enrolleeDetail.setMemberType(clientEnrolleeDetail.getMemberType());
			enrolleeDetail.setNumberOfFacilities(clientEnrolleeDetail.getNumberOfFacilities());
			enrolleeDetail.setPcpproviders(getWSPcpproviders(clientEnrolleeDetail.getPcpproviders()));
			enrolleeDetail.setPcpValidationFlag(clientEnrolleeDetail.isPcpValidationFlag());
			enrolleeDetail.setRecordIdentifier(clientEnrolleeDetail.getRecordIdentifier());
			enrolleeDetails.add(enrolleeDetail);
		});
		return enrolleeDetails;
	}

	private List<PcpProvider> getWSPcpproviders(List<com.deltadental.platform.pcp.stub.PcpProvider> pcpproviders) {
		List<PcpProvider> pcpProviderList = new ArrayList<>();
		pcpproviders.forEach(clientPcpProvider -> {
			PcpProvider pcpprovider = new PcpProvider();
			pcpprovider.setBusinesslevels(getWSBusinesslevels(clientPcpProvider.getBusinesslevels()));
			pcpprovider.setDistance(clientPcpProvider.getDistance());
			pcpprovider.setFacilityLatitude(clientPcpProvider.getFacilityLatitude());
			pcpprovider.setFacilityLongitude(clientPcpProvider.getFacilityLongitude());
			pcpprovider.setGroupPracticeNumber(clientPcpProvider.getGroupPracticeNumber());
			pcpprovider.setLanguages(clientPcpProvider.getLanguages());
			pcpprovider.setMaxAge(clientPcpProvider.getMaxAge());
			pcpprovider.setMaxMemberCount(clientPcpProvider.getMaxMemberCount());
			pcpprovider.setMinAge(clientPcpProvider.getMinAge());
			pcpprovider.setMtvNetworkId(clientPcpProvider.getMtvNetworkId());
			pcpprovider.setOfficeHours(getWSOfficeHours(clientPcpProvider.getOfficeHours()));
			pcpprovider.setPatientCount(clientPcpProvider.getPatientCount());
			pcpprovider.setPracticeLocationNumber(clientPcpProvider.getPracticeLocationNumber());
			pcpprovider.setPracticeLocationPhone(clientPcpProvider.getPracticeLocationPhone());
			pcpprovider.setProviderAccessibleFacility(clientPcpProvider.getProviderAccessibleFacility());
			pcpprovider.setProviderAddress(getWSProviderAddress(clientPcpProvider.getProviderAddress()));
			pcpprovider.setProviderFacilityId(clientPcpProvider.getProviderFacilityId());
			pcpprovider.setProviderFacilityName(clientPcpProvider.getProviderFacilityName());
			pcpprovider.setProviderType(clientPcpProvider.getProviderType());
			pcpprovider.setProviderWorkMode(clientPcpProvider.getProviderWorkMode());
			pcpprovider.setSpecialtyCode(clientPcpProvider.getSpecialtyCode());
			pcpprovider.setSpecialtyDescription(clientPcpProvider.getSpecialtyDescription());
			pcpProviderList.add(pcpprovider);
		});
		return pcpProviderList;
	}

	private Address getWSProviderAddress(com.deltadental.platform.pcp.stub.Address providerAddress) {
		
		Address address = new Address();
		try {
			BeanUtils.copyProperties(address, providerAddress);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error(e.getMessage());
		}
		return address;
	}

	private OfficeHour getWSOfficeHours(com.deltadental.platform.pcp.stub.OfficeHour officeHours) {
		OfficeHour oh = new OfficeHour();
		oh.setOfficeHours(officeHours.getOfficeHours());
		return oh;
	}

	private List<BusinessLevels> getWSBusinesslevels(List<com.deltadental.platform.pcp.stub.BusinessLevels> businesslevels) {
		List<BusinessLevels> domainBls = new ArrayList<BusinessLevels>();
		businesslevels.forEach(wsBl -> {
			BusinessLevels domainBl = new BusinessLevels();
			try {
				BeanUtils.copyProperties(domainBl, wsBl);
			} catch (IllegalAccessException | InvocationTargetException e) {
				log.error(e.getMessage());
			}
			domainBls.add(domainBl);
		});

		return domainBls;
	}
	
	private List<BusinessLevels> getWSBusinesslevelsFromBpBls(List<com.deltadental.platform.pcp.stub.BpBusinessLevels> bpBusinessLevels) {
		List<BusinessLevels> domainBpBusinessLevels = new ArrayList<BusinessLevels>();
		bpBusinessLevels.forEach(wsBpBl -> {
			BusinessLevels domainBusinessLevels = new BusinessLevels();
			try {
				BeanUtils.copyProperties(domainBusinessLevels, wsBpBl);
			} catch (IllegalAccessException | InvocationTargetException e) {
				log.error(e.getMessage());
			}
			domainBpBusinessLevels.add(domainBusinessLevels);
		});

		return domainBpBusinessLevels;
	}

	private List<BPBusinessLevels> getWSBpBusinesslevels(List<com.deltadental.platform.pcp.stub.BpBusinessLevels> bpBusinessLevels) {
		List<BPBusinessLevels> domainBpBusinessLevels = new ArrayList<BPBusinessLevels>();
		bpBusinessLevels.forEach(wsBpBl -> {
			BPBusinessLevels domainBpBl = new BPBusinessLevels();
			try {
				BeanUtils.copyProperties(domainBpBl, wsBpBl);
			} catch (IllegalAccessException | InvocationTargetException e) {
				log.error(e.getMessage());
			}
			domainBpBusinessLevels.add(domainBpBl);
		});

		return domainBpBusinessLevels;
	}
	
	private List<BenefitPackage> getWSBenefitPackages(List<com.deltadental.platform.pcp.stub.BenefitPackage> benefitpackages) {
		List<BenefitPackage> domainsBps = new ArrayList<BenefitPackage>();
		benefitpackages.forEach(wsBps -> {
			BenefitPackage domainsBp = new BenefitPackage();
			try {
				BeanUtils.copyProperties(domainsBp, wsBps);
			} catch (IllegalAccessException | InvocationTargetException e) {
				log.error(e.getMessage());
			}
			domainsBps.add(domainsBp);
		});
		return domainsBps;
	}

}
