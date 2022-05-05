package com.deltadental.pcp.search.service.impl;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltadental.pcp.search.domain.Address;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.entities.ProvidersAuditEntity;
import com.deltadental.pcp.search.repos.ProvidersAuditRepo;
import com.deltadental.pcp.search.service.ProvidersAuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Service("providersAuditService")
@Slf4j
public class ProvidersAuditServiceImpl implements ProvidersAuditService {

	private static final DateTimeFormatter _FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	
	@Autowired
	private ProvidersAuditRepo providersAuditRepo;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void saveProvidersAudit(ProvidersRequest providersRequest, ProvidersResponse providersResponse) {
		try {
			ProvidersAuditEntity providersAuditEntity = getProvidersAuditEntity(providersRequest, providersResponse);
			providersAuditRepo.save(providersAuditEntity);
		} catch (Exception exception) {
			log.error("Unable to save providers audit information into datastore", exception);
		}
	}

	private ProvidersAuditEntity getProvidersAuditEntity(ProvidersRequest providersRequest, ProvidersResponse providersResponse) throws SerialException, SQLException {
		ProvidersAuditEntity providersAuditEntity = new ProvidersAuditEntity();
		providersAuditEntity.setProvidersAuditId(UUID.randomUUID().toString());
		providersAuditEntity.setContractId(StringUtils.trimToNull(providersRequest.getContractID()));
		providersAuditEntity.setMemberId(StringUtils.trimToNull(providersRequest.getMemberId()));
		String pcpEffectiveDate = providersRequest.getPcpEffectiveDate().format(_FORMATTER);
		providersAuditEntity.setPcpEffectiveDate(StringUtils.trimToNull(pcpEffectiveDate));
		providersAuditEntity.setSourceSystem(StringUtils.trimToNull(providersRequest.getSourceSystem()));
		providersAuditEntity.setUserId(StringUtils.trimToNull(providersRequest.getUserId()));
		providersAuditEntity.setAutoAssignment(providersRequest.getAutoAssignment());		
		Address address = providersRequest.getAddress();
		providersAuditEntity.setAddressLineOne(StringUtils.trimToNull(address.getAddressLine1()));
		providersAuditEntity.setAddressLineTwo(StringUtils.trimToNull(address.getAddressLine2()));
		providersAuditEntity.setCity(StringUtils.trimToNull(address.getCity()));
		providersAuditEntity.setState(StringUtils.trimToNull(address.getState()));
		providersAuditEntity.setZipCode(StringUtils.trimToNull(address.getZipCode()));
		if(providersResponse != null) {
			String jsonStr = getJsonString(providersResponse);
			providersAuditEntity.setResponseJson(StringUtils.trimToNull(jsonStr));
		}
		return providersAuditEntity;
		
	}
	
	private String getJsonString(ProvidersResponse providersResponse) {
		try {
		    return mapper.writeValueAsString(providersResponse);
		} catch (JsonProcessingException  e) {
			log.error("Exception parsing provider response : ", e);
		    return null;
		}
	}
}
