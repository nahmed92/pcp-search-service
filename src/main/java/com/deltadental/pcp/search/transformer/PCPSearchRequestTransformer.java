package com.deltadental.pcp.search.transformer;

import java.time.format.DateTimeFormatter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.platform.pcp.stub.PcpProviderRequest;
import com.deltadental.platform.pcp.stub.PcpSearchRequest;
import com.deltadental.platform.pcp.stub.PcpValidate;
import com.deltadental.platform.pcp.stub.Providers;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PCPSearchRequestTransformer {

	private static final DateTimeFormatter _FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");	
	
	/**
	 * This method maps pcp validate request to soap pcpvalidate
	 * @param pcpValidateRequest
	 * @return PcpValidate
	 */
	public PcpValidate transformPcpValidateRequest(PCPValidateRequest pcpValidateRequest) {
		log.info("START PCPSearchRequestTransformer.transformPcpValidateRequest");
		PcpValidate pcpValidate = new PcpValidate();
		if(pcpValidateRequest != null) {
			PcpProviderRequest pcpProviderRequest = new PcpProviderRequest();
			pcpProviderRequest.setContractId(pcpValidateRequest.getContractId());
			pcpProviderRequest.setLookAheadDays(pcpValidateRequest.getLookAheadDays());
			pcpProviderRequest.setMemberType(pcpValidateRequest.getMemberType());
			pcpProviderRequest.setMtvPersonId(pcpValidateRequest.getMtvPersonId());
			String pcpEffectiveDate = pcpValidateRequest.getPcpEffDate().format(_FORMATTER);
			pcpProviderRequest.setPcpEffDate(pcpEffectiveDate);
			String pcpEndDate = pcpValidateRequest.getPcpEndDate().format(_FORMATTER);
			pcpProviderRequest.setPcpEndDate(pcpEndDate);
			pcpProviderRequest.setProduct(pcpValidateRequest.getProduct());
			pcpProviderRequest.setProviderId(pcpValidateRequest.getProviderId());
			pcpProviderRequest.setRecordIdentifier(pcpValidateRequest.getRecordIdentifier());
			pcpProviderRequest.setSourceSystem(pcpValidateRequest.getSourceSystem());
			pcpValidate.setArg0(pcpProviderRequest);
		}
		
		log.info("END PCPSearchRequestTransformer.transformPcpValidateRequest");
		return pcpValidate;
	}
	
	/**
	 * This method transforms providers request to soap providers request
	 * @param providersRequest
	 * @return Providers
	 */
	public Providers transformProvidersRequest(ProvidersRequest providersRequest) {
		log.info("START PCPSearchRequestTransformer.transformProviderValidate");
		Providers providers = new Providers();
		PcpSearchRequest pcpSearchRequest = new PcpSearchRequest();
		pcpSearchRequest.setContractID(providersRequest.getContractId());
		pcpSearchRequest.setMemberId(providersRequest.getMemberId());
		pcpSearchRequest.setZipcode(StringUtils.trimToEmpty(providersRequest.getAddress().getZipCode()));
		pcpSearchRequest.setPcpEffectiveDate(providersRequest.getPcpEffectiveDate());
		pcpSearchRequest.setSourceSystem(providersRequest.getSourceSystem());
		pcpSearchRequest.setAutoAssignment(providersRequest.getAutoAssignment());
		pcpSearchRequest.setAddress(mapStubAddress(providersRequest.getAddress()));
		providers.setArg0(pcpSearchRequest);
		log.info("START PCPSearchRequestTransformer.transformProviderValidate");
		return providers;
	}
	
	/**
	 * This method maps address domain object to sopa address object
	 * @param address
	 * @return Address
	 */
	private com.deltadental.platform.pcp.stub.Address mapStubAddress(Address address) {
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
}
