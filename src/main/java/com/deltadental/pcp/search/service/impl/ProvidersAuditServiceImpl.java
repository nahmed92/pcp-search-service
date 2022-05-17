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
		providersAuditEntity.setId(UUID.randomUUID().toString());
		providersAuditEntity.setRequestJson(getJsonString(providersRequest));
		if(providersResponse != null) {
			providersAuditEntity.setResponseJson(getJsonString(providersResponse));
		}
		return providersAuditEntity;
		
	}
	
	private String getJsonString(Object object) {
		try {
		    return mapper.writeValueAsString(object);
		} catch (JsonProcessingException  e) {
			log.error("Exception parsing provider response : ", e);
		    return null;
		}
	}
}
